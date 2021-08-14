package com.sprintgether.otserver.service.auth.impl;

import com.sprintgether.otserver.controller.AuthController;
import com.sprintgether.otserver.exception.*;
import com.sprintgether.otserver.model.dto.PasswordResetLinkRequest;
import com.sprintgether.otserver.model.dto.PasswordResetRequest;
import com.sprintgether.otserver.model.dto.UpdatePasswordRequest;
import com.sprintgether.otserver.model.entity.Role;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.EnumTokenStatus;
import com.sprintgether.otserver.model.enums.EnumTokenType;
import com.sprintgether.otserver.model.payload.RegistrationRequest;
import com.sprintgether.otserver.repository.UserRepository;
import com.sprintgether.otserver.security.CustomUserDetails;
import com.sprintgether.otserver.security.jwt.JwtTokenProvider;
import com.sprintgether.otserver.service.core.RoleService;
import com.sprintgether.otserver.service.core.TokenService;
import com.sprintgether.otserver.service.core.UserService;
import com.sprintgether.otserver.service.auth.AuthService;
import com.sprintgether.otserver.util.TokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    @Value("${app.token.password.reset.duration}")
    private String passwordResetDuration;

    @Value("${app.roles.user}")
    private String roleUser;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public Optional<Authentication> authenticateUser(String email, String password) {
        return Optional.ofNullable(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,
                password)));
    }

    @Override
    public String generateAccessToken(String email) {
        return jwtTokenProvider.generateJwtToken(email);
    }

    @Override
    public User registerUser(RegistrationRequest registrationRequest) throws ResourceAlreadyInUseException, OtDBItemNotFoundException {
        boolean emailAlreadyExists = emailAlreadyExists(registrationRequest.getEmail());
        if (emailAlreadyExists)
            throw new ResourceAlreadyInUseException("Email", "Address", registrationRequest.getEmail());
       return saveUserFromRequest(registrationRequest);
    }

    private User saveUserFromRequest(RegistrationRequest registrationRequest) throws OtDBItemNotFoundException {
        User user = User.builder()
                .email(registrationRequest.getEmail())
                .password(encoder.encode(registrationRequest.getPassword()))
                .build();
        Role userRole = roleService.findById(roleUser);
        user.addRole(userRole);
        return userRepository.save(user);
    }

    @Override
    public boolean emailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> processEmailVerificattion(String token) {
        Token emailVerificationToken = tokenService.findByToken(token)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Token", "Email verification", token)
                    );
        User registeredUser = emailVerificationToken.getUser();

        if (registeredUser.isEmailVerified())
            return Optional.of(registeredUser);

        tokenService.verifyExpiration(emailVerificationToken);
        emailVerificationToken.setVerified();
        tokenService.save(emailVerificationToken);
        registeredUser.verifyEmail();
        userRepository.save(registeredUser);

        return Optional.of(registeredUser);
    }

    @Override
    public Boolean currentPasswordMatches(User currentUser, String password){
        return passwordEncoder.matches(password, currentUser.getPassword());
    }

    @Override
    public Optional<User> updatePassword(CustomUserDetails customUserDetails, UpdatePasswordRequest updatePasswordRequest) {
        User currentUser = userService.findByEmail(customUserDetails.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User to update", "Email adress", customUserDetails.getUsername()
                )
        );

        boolean passwordMatch = currentPasswordMatches(currentUser, updatePasswordRequest.getOldPassword());
        if(!passwordMatch)
            throw new UpdatePasswordException(currentUser.getEmail(), "Invalid current Password");

        String newPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        currentUser.setPassword(newPassword);
        userService.save(currentUser);

        return Optional.of(currentUser);
    }

    @Override
    public Optional<Token> generatePasswordResetToken(PasswordResetLinkRequest passwordResetLinkRequest) {
        String email = passwordResetLinkRequest.getEmail();

        User user = userService.findByEmail(email).orElseThrow(
                () -> new PasswordResetLinkException(email, "No matching user found for the given request")
        );

        Token passwordResetToken = tokenService.createTokenByType(
                user,
                EnumTokenType.PASSWORD_RESET_TOKEN,
                EnumTokenStatus.VERIFIED,
                TokenUtil.generateRandomDigitsLettersCode(12),
                Long.valueOf(passwordResetDuration)
        );

        tokenService.save(passwordResetToken);
        System.out.println("============================================");
        System.out.println(passwordResetToken.getValue());
        System.out.println("============================================");

        return Optional.ofNullable(passwordResetToken);
    }

    @Override
    public Optional<User> resetPassword(PasswordResetRequest passwordResetRequest) {
        String token = passwordResetRequest.getTokenString();
        Token passwordResetToken = tokenService.findByToken(token)
        .orElseThrow(
                () -> new ResourceNotFoundException("Password reset Token", "Token Id", token)
        );

        tokenService.verifyExpiration(passwordResetToken);
        String encodedPassword = passwordEncoder.encode(passwordResetRequest.getPassword());

        User user = passwordResetToken.getUser();
        user.setPassword(encodedPassword);
        userService.save(user);

        return Optional.of(user);
    }

}
