package capstone.mauriziocrispino.MaurizioCrispino.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Feedback {
    @Id
    @GeneratedValue
    private long id;
    private String feedback;
    private int votoFeedback;
    @ManyToOne
    @JoinColumn(name = "provincia", nullable = false)
    private Utente utente;
}
