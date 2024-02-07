package capstone.mauriziocrispino.MaurizioCrispino.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public String getFeedback() {
        return feedback;
    }

    public int getVotoFeedback() {
        return votoFeedback;
    }

    public Utente getUtente() {
        return utente;
    }
}
