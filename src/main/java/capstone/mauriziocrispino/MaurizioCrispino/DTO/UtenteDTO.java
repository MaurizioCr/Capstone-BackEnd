package capstone.mauriziocrispino.MaurizioCrispino.DTO;

import capstone.mauriziocrispino.MaurizioCrispino.Enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UtenteDTO (@NotEmpty(message = "Il nome è un campo obbligatorio!")
@Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String cognome,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "L'username è un campo obbligatorio!")
        String username,

        @NotEmpty(message = "Scegli un avatar!")
        String avatar,
        Role role) {

}

