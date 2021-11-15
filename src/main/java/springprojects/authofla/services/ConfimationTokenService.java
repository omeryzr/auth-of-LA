package springprojects.authofla.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springprojects.authofla.entities.ConfirmationToken;
import springprojects.authofla.repositories.ConfirmationTokenRepository;

@Service
@AllArgsConstructor
public class ConfimationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }
}

