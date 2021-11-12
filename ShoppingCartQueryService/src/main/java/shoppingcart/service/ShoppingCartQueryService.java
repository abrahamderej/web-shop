package shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.data.ShoppingQueryRepository;
import shoppingcart.domain.CartLine;
import shoppingcart.domain.Product;
import shoppingcart.domain.ShoppingCart;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartQueryService {

    @Autowired
    ShoppingQueryRepository shoppingQueryRepository;

    public ShoppingCartDTO getShoppingCart(int cartId) {
        Optional<ShoppingCart> cart = shoppingQueryRepository.findById(cartId);
        if (cart.isPresent())
            return ShoppingCartAdapter.getShoppingCartDTO(cart.get());
        else
            return null;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingQueryRepository.findAll();
    }

    public void addToCart(ShoppingCart shoppingCart) {
        shoppingCart.setEventTime(LocalDateTime.now());
        shoppingQueryRepository.save(shoppingCart);
    }

}
