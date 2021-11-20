package springprojects.authofla.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springprojects.authofla.entities.User;
import springprojects.authofla.repositories.UserRepository;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users/")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/profile")
    public Optional<User> myProfile(Principal principal){
        return userRepository.findByEmail(principal.getName());
    }
}
