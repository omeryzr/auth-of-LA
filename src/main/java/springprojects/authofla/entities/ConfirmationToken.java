package springprojects.authofla.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class ConfirmationToken {

    @SequenceGenerator(name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence")
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expriesAt;

    private LocalDateTime confirmedAt;


    //TODO: make a relationship between confirmationToken and User



    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expriesAt) {
        this.token = token;
        this.createdAt = createdAt;
        this.expriesAt = expriesAt;
    }

    public ConfirmationToken() {
    }
}

