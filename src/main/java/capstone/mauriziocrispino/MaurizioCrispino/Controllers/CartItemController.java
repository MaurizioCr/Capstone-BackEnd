package capstone.mauriziocrispino.MaurizioCrispino.Controllers;

import capstone.mauriziocrispino.MaurizioCrispino.Services.OggettoCarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
    @Autowired
    private OggettoCarrelloService oggettoCarrelloService;

    @PostMapping("/{cartId}/{productId}")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        oggettoCarrelloService.addItemToCart(cartId, productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        oggettoCarrelloService.removeItemFromCart(cartId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}