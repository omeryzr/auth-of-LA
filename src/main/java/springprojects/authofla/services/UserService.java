package springprojects.authofla.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springprojects.authofla.entities.ConfirmationToken;
import springprojects.authofla.entities.User;
import springprojects.authofla.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfimationTokenService confimationTokenService;

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

        String token = UUID.randomUUID().toString();
        // TODO: Send Confirmation Token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confimationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: send e-mail

        return token;
    }

    public int enableAppUser(String email) {
        return userRepository.enableUser(email);
    }
}
