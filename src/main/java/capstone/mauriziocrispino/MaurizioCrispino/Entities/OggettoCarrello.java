package capstone.mauriziocrispino.MaurizioCrispino.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OggettoCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @Column(name = "data_aggiunta")
    @CreationTimestamp
    private LocalDateTime dataAggiunta;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OggettoCarrello oggettoCarrello = (OggettoCarrello) o;
        return Objects.equals(cart, oggettoCarrello.cart) && Objects.equals(products, oggettoCarrello.products);
    }


}