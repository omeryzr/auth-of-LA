package springprojects.authofla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springprojects.authofla.entities.RegistrationRequest;
import springprojects.authofla.entities.User;
import springprojects.authofla.entities.UserRole;

@Service
@AllArgsConstructor
public class RegistrationService{

    private final UserService userService;
    private EmailValidator emailValidator;

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
}