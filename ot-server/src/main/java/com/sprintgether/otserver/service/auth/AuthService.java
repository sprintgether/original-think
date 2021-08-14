package com.sprintgether.otserver.service.auth;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.exception.ResourceAlreadyInUseException;
import com.sprintgether.otserver.model.dto.PasswordResetLinkRequest;
import com.sprintgether.otserver.model.dto.PasswordResetRequest;
import com.sprintgether.otserver.model.dto.UpdatePasswordRequest;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.payload.RegistrationRequest;
import com.sprintgether.otserver.security.CustomUserDetails;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthService {

    Optional<Authentication> authenticateUser(String email, String password);

    String generateAccessToken(String email);

    User registerUser(RegistrationRequest registrationRequest) throws ResourceAlreadyInUseException, OtDBItemNotFoundException;

    boolean emailAlreadyExists(String email);

    Optional<User> processEmailVerificattion(String token);

    Optional<User> updatePassword(CustomUserDetails customUserDetails, UpdatePasswordRequest updatePasswordRequest);

    Boolean currentPasswordMatches(User currentUser, String password);

    Optional<User> resetPassword(PasswordResetRequest passwordResetRequest);

    Optional<Token> generatePasswordResetToken(PasswordResetLinkRequest passwordResetLinkRequest);

}
