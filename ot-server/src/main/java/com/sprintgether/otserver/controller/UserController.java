package com.sprintgether.otserver.controller;

import com.sprintgether.otserver.annotation.CurrentUser;
import com.sprintgether.otserver.event.OnUserAccountChangeEvent;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.exception.UpdatePasswordException;
import com.sprintgether.otserver.model.dto.UpdatePasswordRequest;
import com.sprintgether.otserver.model.dto.UserDto;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import com.sprintgether.otserver.model.payload.RestResponse;
import com.sprintgether.otserver.security.CustomUserDetails;
import com.sprintgether.otserver.service.auth.AuthService;
import com.sprintgether.otserver.service.core.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/me")
    public RestResponse me(@CurrentUser CustomUserDetails customUserDetails) throws OtDBItemNotFoundException {
        return new RestResponse(UserDto.fromEntity(userService.findById(customUserDetails.getId())), "Mon compte", ResponseStatus.SUCCESS, 200);
    }

    @ApiOperation(value = "Permet à un utilisateur de modifier son mot de passe une fois connecté")
    @PostMapping("/update/password")
    public RestResponse updatePassword(@CurrentUser CustomUserDetails customUserDetails, @Valid @RequestBody UpdatePasswordRequest updatePasswordRequest){
        User updatedUser = authService.updatePassword(customUserDetails, updatePasswordRequest)
                .orElseThrow(
                        () -> new UpdatePasswordException("-Empty-", "No such user present")
                );
        OnUserAccountChangeEvent onUserAccountChangeEvent =
                new OnUserAccountChangeEvent(updatedUser, "Update Password", "Change successful");
        applicationEventPublisher.publishEvent(onUserAccountChangeEvent);

        return new RestResponse("Password changed successfully", ResponseStatus.SUCCESS, 200);
    }
}
