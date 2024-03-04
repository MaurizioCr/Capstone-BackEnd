package capstone.mauriziocrispino.MaurizioCrispino.Services;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Cart;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.OggettoCarrello;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Products;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.OggettoCarrelloRepository;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OggettoCarrelloService {
    @Autowired
    private OggettoCarrelloRepository cartItemsRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productsService;
    @Autowired
    private CarrelloRepository carrelloRepository;


    public void addItemToCart(Long cartId, Long productId) {
        Cart cart = cartService.getCartById(cartId);
        Products products = productsService.getProductById(productId);
        List<OggettoCarrello> oggettoCarrellos = cart.getOggettoCarrellos();
        OggettoCarrello oggettoCarrello = new OggettoCarrello();
        oggettoCarrello.setCart(cart);
        oggettoCarrello.setProducts(products);
        oggettoCarrello.setQuantity(1);
        cart.setTotalPrice(cart.getTotalPrice() + products.getPrice());
        cartService.updateCart(cart);
        if (oggettoCarrellos.contains(oggettoCarrello)) {
            OggettoCarrello found = cartItemsRepository.findByCartIdAndProductsId(cartId, productId);
            found.setQuantity(found.getQuantity() + 1);
            cartItemsRepository.save(found);

        } else {
            cartItemsRepository.save(oggettoCarrello);
        }
    }


    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCartById(cartId);
        Products products = productsService.getProductById(productId);
        OggettoCarrello found = cartItemsRepository.findByCartIdAndProductsId(cartId, productId);
        cart.setTotalPrice(cart.getTotalPrice() - products.getPrice());
        cartService.updateCart(cart);
        if (found.getQuantity() >= 2) {
            found.setQuantity(found.getQuantity() - 1);
            cartItemsRepository.save(found);
        } else {
            cartItemsRepository.delete(found);
        }

    }


}