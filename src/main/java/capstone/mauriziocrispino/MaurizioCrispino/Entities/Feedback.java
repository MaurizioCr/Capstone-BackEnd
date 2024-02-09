package capstone.mauriziocrispino.MaurizioCrispino.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity

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
    @JoinColumn(name = "Utente", nullable = false)
    private Utente utente;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getVotoFeedback() {
        return votoFeedback;
    }

    public void setVotoFeedback(int votoFeedback) {
        this.votoFeedback = votoFeedback;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
