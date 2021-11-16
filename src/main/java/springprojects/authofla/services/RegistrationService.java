package springprojects.authofla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springprojects.authofla.entities.ConfirmationToken;
import springprojects.authofla.entities.RegistrationRequest;
import springprojects.authofla.entities.User;
import springprojects.authofla.entities.UserRole;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService{

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;


    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                 new User(
                       request.getFirstName(),
                       request.getLastName(),
                         request.getEmail(),
                         request.getPassword(),
                         UserRole.USER
                 )
        );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}