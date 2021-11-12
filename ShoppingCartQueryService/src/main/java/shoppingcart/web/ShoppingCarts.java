package shoppingcart.web;



import shoppingcart.domain.ShoppingCart;

import java.util.Collection;

public class ShoppingCarts {
    private Collection<ShoppingCart> shoppingCarts;

    public ShoppingCarts() {
    }

    public ShoppingCarts(Collection<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public Collection<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Collection<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public String toString() {
        return "ShoppingCarts{" +
                "shoppingCarts=" + shoppingCarts +
                '}';
    }
}
