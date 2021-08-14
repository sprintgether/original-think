package com.sprintgether.otserver.controller;

import com.sprintgether.otserver.event.OnGenerateResetLinkEvent;
import com.sprintgether.otserver.event.OnUserAccountChangeEvent;
import com.sprintgether.otserver.event.OnUserRegistrationCompleteEvent;
import com.sprintgether.otserver.exception.InvalidTokenRequestException;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.exception.PasswordResetException;
import com.sprintgether.otserver.exception.PasswordResetLinkException;
import com.sprintgether.otserver.model.dto.PasswordResetLinkRequest;
import com.sprintgether.otserver.model.dto.PasswordResetRequest;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import com.sprintgether.otserver.model.payload.JwtResponse;
import com.sprintgether.otserver.model.payload.LoginRequest;
import com.sprintgether.otserver.model.payload.RegistrationRequest;
import com.sprintgether.otserver.model.payload.RestResponse;
import com.sprintgether.otserver.security.CustomUserDetails;
import com.sprintgether.otserver.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Optional;

import static com.sprintgether.otserver.common.AppConstant.CLIENT_ORIGIN_HEADER;

@RestController
@RequestMapping("/auth")
@Api(value = "API de la gestion de l'authentification")
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Value("${app.client.default-url}")
    private String clientDefaultUrl;

    @Value("${app.client.verify.email.path}")
    private String clientVerifyEmailPath;

    @Value("${app.client.reset.password.path}")
    private String clientResetPasswordPath;

    @Autowired
    private AuthService authService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public RestResponse register(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult errors, HttpServletRequest request) throws OtDBItemNotFoundException {
        if (errors.hasErrors()) {
            throw new ValidationException(errors.toString());
        }

        User registeredUser = authService.registerUser(registrationRequest);

         String clientUrl = request.getHeader(CLIENT_ORIGIN_HEADER) == null ? clientDefaultUrl : request.getHeader("origin");
          UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(clientUrl + clientVerifyEmailPath);

        OnUserRegistrationCompleteEvent onUserRegistrationCompleteEvent =
                new OnUserRegistrationCompleteEvent(registeredUser, urlBuilder);

         applicationEventPublisher.publishEvent(onUserRegistrationCompleteEvent);
        return new RestResponse("Enregistrement effectuée avec succès. Veuillez consulter votre boîte de messagerie pour vérifier votre adresse mail.", ResponseStatus.SUCCESS, 200);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Authentifier un utilisateur sur le système")
    public RestResponse login(@Valid @RequestBody LoginRequest loginRequest,  BindingResult errors){
        if (errors.hasErrors()) {
            throw new ValidationException(errors.toString());
        }

        LOGGER.debug("Authenticating dealer with username {}", loginRequest.getUsername());

        Authentication authentication = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new RuntimeException("Couldn't authenticate dealer [" + loginRequest + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = authService.generateAccessToken(customUserDetails.getUsername());

        return new RestResponse(new JwtResponse(accessToken, customUserDetails.getAuthorities()), "Connexion effectuée avec succès!", ResponseStatus.SUCCESS, 200);
    }

    @PostMapping("/verify/email")
    @ApiOperation(value = "Vérifier l'adresse mail de l'utilisateur")
    public RestResponse verifyEmail(@RequestParam("token") String token) throws InvalidTokenRequestException {
        User user = authService.processEmailVerificattion(token)
                .orElseThrow(()-> new InvalidTokenRequestException("Un problème est survenu lors de la verification", token, "xxx"));

        return new RestResponse(user.getEmail(), "Adresse email vérifiée avec succès!", ResponseStatus.SUCCESS, 200);
    }

    @ApiOperation(value = "Génère le lien de réinitialisation du mot de passe et déclenche l'évènement de l'envoi de mail contenant le lien (et le code) de réinitialisation du mot de passe.")
    @PostMapping("/forgot/password")
    public RestResponse forgotPassword(@Valid @RequestBody PasswordResetLinkRequest passwordResetLinkRequest, BindingResult errors, HttpServletRequest request) {
        if(errors.hasErrors()){
            throw new ValidationException(errors.toString());
        }

        Token passwordResetToken = authService.generatePasswordResetToken(passwordResetLinkRequest)
                .orElseThrow(() -> new PasswordResetLinkException(
                        passwordResetLinkRequest.getEmail(), "Impossible de créer un token valide")
                );
        String clientUrl = request.getHeader(CLIENT_ORIGIN_HEADER) == null ? clientDefaultUrl : request.getHeader("origin");

        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(clientUrl + clientResetPasswordPath);

        OnGenerateResetLinkEvent onGenerateResetLinkEvent = new OnGenerateResetLinkEvent(passwordResetToken, urlBuilder);

        applicationEventPublisher.publishEvent(onGenerateResetLinkEvent);
        return new RestResponse("Le lien de récupération du mot de passe vous a été envoyé avec succès, veuillez consulter votre boîte mail.",
                ResponseStatus.SUCCESS, 200);
    }

    @ApiOperation(value = "reçois un code d'initialisation du mot passe, réinitialise le mot de passe puis décenche l'envoi du mail de changement du mot de passe")
    @PostMapping("/reset/password")
    public RestResponse resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest, BindingResult errors){
        if(errors.hasErrors()){
            throw new ValidationException(errors.toString());
        }

        User changedUser = authService.resetPassword(passwordResetRequest)
                .orElseThrow(
                        () -> new PasswordResetException(passwordResetRequest.getTokenString(), "Error in resetting password")
                );
        OnUserAccountChangeEvent onUserAccountChangeEvent = new OnUserAccountChangeEvent(
                changedUser, "Reset Password", "Your password is reset successfully. You can use the new password to login on Orifinal Think");

        applicationEventPublisher.publishEvent(onUserAccountChangeEvent);
        return new RestResponse("Your password is reset successfully ! Please click on the Sign In button above to login with your new password", ResponseStatus.SUCCESS, 200);
    }

}
