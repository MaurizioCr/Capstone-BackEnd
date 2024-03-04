package capstone.mauriziocrispino.MaurizioCrispino.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "utente_id")
    private Utente user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<OggettoCarrello> oggettoCarrellos = new ArrayList<>();

    private int totalPrice;


}