package shoppingcart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppingcart.service.ShoppingCartDTO;
import shoppingcart.service.ShoppingCartQueryService;


@RestController
@RequestMapping("/qshoppingcarts")
public class ShoppingCartController {

    @Autowired
    ShoppingCartQueryService shoppingCartService;


    @GetMapping("/carts/{cartId}")
    public ResponseEntity<?> getShoppingCart(@PathVariable int cartId) {
        ShoppingCartDTO shoppingCart = shoppingCartService.getShoppingCart(cartId);
        if (shoppingCart == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("ShoppingCart with cart id = " + cartId + "is not Exist"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ShoppingCartDTO>(shoppingCart, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllShoppingCarts() {
        ShoppingCarts shoppingCarts = new ShoppingCarts(shoppingCartService.getAllShoppingCarts());
        return new ResponseEntity<ShoppingCarts>(shoppingCarts, HttpStatus.OK);
    }


}
