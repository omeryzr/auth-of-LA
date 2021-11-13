package springprojects.authofla.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springprojects.authofla.entities.User;
import springprojects.authofla.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG)));
    }

    public String signUpUser(User user){
        boolean existUser = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if (existUser){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);
        userRepository.save(user);

        // TODO: Send Confirmation Token
        return "it works";
    }
}
