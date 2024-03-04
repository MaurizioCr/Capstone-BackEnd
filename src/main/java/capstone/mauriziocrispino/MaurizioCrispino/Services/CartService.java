package capstone.mauriziocrispino.MaurizioCrispino.Services;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Cart;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Utente;
import capstone.mauriziocrispino.MaurizioCrispino.Exceptions.NotFoundException;
import capstone.mauriziocrispino.MaurizioCrispino.Repositories.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CarrelloRepository carrelloRepository;


    public Cart createCart(Utente user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return carrelloRepository.save(cart);
    }

    public Cart getCartById(Long cartId) {
        return carrelloRepository.findById(cartId).orElseThrow(() -> new NotFoundException(cartId));
    }

    public void updateCart(Cart cart) {
        carrelloRepository.save(cart);
    }

    public void deleteCart(Long cartId) {
        carrelloRepository.deleteById(cartId);
    }


    public void updateCartItemQuantity(Long cartId, Long cartItemId, int newQuantity) {
        Optional<Cart> optionalCart = carrelloRepository.findById(cartId);
        optionalCart.ifPresent(cart -> {
            cart.getOggettoCarrellos().stream()
                    .filter(item -> item.getId().equals(cartItemId))
                    .findFirst()
                    .ifPresent(item -> {
                        item.setQuantity(newQuantity);
                        carrelloRepository.save(cart);
                    });
        });
    }


}