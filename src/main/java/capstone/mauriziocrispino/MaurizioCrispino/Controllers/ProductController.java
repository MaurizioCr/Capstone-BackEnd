package capstone.mauriziocrispino.MaurizioCrispino.Controllers;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Products;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @PostMapping
    public Products createProduct(@RequestBody Products product) {
        return prodottoRepository.save(product);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Long id, @RequestBody Products product) {
        product.setId(id);
        return prodottoRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        prodottoRepository.deleteById(id);
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return prodottoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Long id) {
        return prodottoRepository.findById(id).orElse(null);
    }



}