package springprojects.authofla.services;

import org.springframework.stereotype.Service;
import springprojects.authofla.entities.RegistrationRequest;

@Service
public class RegistrationService  {
    public String register(RegistrationRequest request){
        return "works";
    }
}