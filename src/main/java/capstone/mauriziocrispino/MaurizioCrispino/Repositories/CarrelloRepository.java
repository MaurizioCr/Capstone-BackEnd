package capstone.mauriziocrispino.MaurizioCrispino.Repositories;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrelloRepository extends JpaRepository <Cart, Long> {
}