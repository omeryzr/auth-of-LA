package springprojects.authofla.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springprojects.authofla.entities.RegistrationRequest;
import springprojects.authofla.services.RegistrationService;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);

    }

    @GetMapping(path = "confirm")
    public String tokenConfirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}