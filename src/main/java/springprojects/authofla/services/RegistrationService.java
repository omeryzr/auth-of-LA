package springprojects.authofla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springprojects.authofla.entities.RegistrationRequest;

@Service
@AllArgsConstructor
public class RegistrationService  {

    private EmailValidator emailValidator;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return "it works";
    }
}