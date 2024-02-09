package capstone.mauriziocrispino.MaurizioCrispino.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record FeedbackDTO(@NotEmpty(message = "Inserisci ciò che pensi!") String feedback , int votoFeedback) {
}
