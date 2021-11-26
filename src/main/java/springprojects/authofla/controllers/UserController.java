package springprojects.authofla.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/profile/{id}")
    public Optional<User> showUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user, Principal principal){
        User userInformation = userRepository.getById(id);
        if(principal.getName().equals(userInformation.getEmail())){
            userInformation.setEmail(user.getEmail());
            userInformation.setFirstName(user.getFirstName());
            userInformation.setLastName(user.getLastName());
            final User updatedUser = userRepository.save(userInformation);
            return ResponseEntity.ok(updatedUser);
        }
            return ResponseEntity.badRequest().body(user);

    }
}
