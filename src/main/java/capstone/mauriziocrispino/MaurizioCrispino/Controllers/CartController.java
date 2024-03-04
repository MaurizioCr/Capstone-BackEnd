package capstone.mauriziocrispino.MaurizioCrispino.Controllers;

import capstone.mauriziocrispino.MaurizioCrispino.Entities.Cart;
import capstone.mauriziocrispino.MaurizioCrispino.Entities.Utente;
import capstone.mauriziocrispino.MaurizioCrispino.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Utente user) {
        Cart cart = cartService.createCart(user);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId) {

        return cartService.getCartById(cartId);


    }

    @PutMapping
    public ResponseEntity<Void> updateCart(@RequestBody Cart cart) {
        cartService.updateCart(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}