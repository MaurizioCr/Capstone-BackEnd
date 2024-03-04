package capstone.mauriziocrispino.MaurizioCrispino.DTO;


import jakarta.validation.constraints.NotEmpty;

public record ProductsDTO (@NotEmpty(message = "The price is required.")
                          int price,
                          @NotEmpty(message = "The description is required.")
                          String description,
                          @NotEmpty(message = "The immagine is required.")
                          String immagine


)  {}
