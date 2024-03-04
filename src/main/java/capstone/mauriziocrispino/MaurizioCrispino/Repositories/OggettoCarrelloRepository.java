package capstone.mauriziocrispino.MaurizioCrispino.Repositories;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.OggettoCarrello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OggettoCarrelloRepository extends JpaRepository<OggettoCarrello, Long> {
    public OggettoCarrello findByCartIdAndProductsId(Long cartId, Long productsId);
}