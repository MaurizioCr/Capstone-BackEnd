package capstone.mauriziocrispino.MaurizioCrispino.Repositories;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Products, Long> {
    }