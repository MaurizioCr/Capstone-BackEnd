package capstone.mauriziocrispino.MaurizioCrispino.Services;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Products;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProdottoRepository prodottoRepository;

    @Autowired
    public ProductService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    public Products createProduct(Products product) {
        return prodottoRepository.save(product);
    }

    public Products updateProduct(Long id, Products product) {
        product.setId(id);
        return prodottoRepository.save(product);
    }

    public void deleteProduct(Long id) {
        prodottoRepository.deleteById(id);
    }

    public List<Products> getAllProducts() {
        return prodottoRepository.findAll();
    }

    public Products getProductById(Long id) {
        return prodottoRepository.findById(id).orElse(null);
    }


}