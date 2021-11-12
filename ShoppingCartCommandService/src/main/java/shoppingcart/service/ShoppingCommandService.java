package shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.data.ShoppingCommandRepository;
import shoppingcart.domain.CartLine;
import shoppingcart.domain.Product;
import shoppingcart.domain.ShoppingCart;

import java.util.Iterator;
import java.util.Optional;

@Service
public class ShoppingCommandService {

    @Autowired
    ShoppingCommandRepository shoppingCommandRepository;
    @Autowired
    Sender sender;

    public void addToCart(int cartId, ProductDTO productDto, int quantity) {
        //create a shopping product from a products product
        Product product = new Product(productDto.getProductNumber(), productDto.getName(),productDto.getPrice(),productDto.getDescription(), productDto.getNumberInStock());
        Optional<ShoppingCart> cartOptional = shoppingCommandRepository.findById(cartId);
        if (cartOptional.isPresent() && product != null) {
            ShoppingCart cart = cartOptional.get();
            cart.addToCart(product, quantity);
            sender.send(cart);
            shoppingCommandRepository.save(cart);
        }
        else if (product != null) {
            ShoppingCart cart = new ShoppingCart();
            cart.setCartId(cartId);
            cart.addToCart(product, quantity);
            sender.send(cart);
            shoppingCommandRepository.save(cart);
        }
    }

    public ShoppingCartDTO getCart(int cartId) {
        Optional<ShoppingCart> cart = shoppingCommandRepository.findById(cartId);
        if (cart.isPresent())
            return ShoppingCartAdapter.getShoppingCartDTO(cart.get());
        else
            return null;
    }


    public void addProductToCart(int cartId, ProductDTO productDto, int quantity) {
        //create a shopping product from a products product
        Product product = new Product(productDto.getProductNumber(), productDto.getName(),productDto.getPrice(),productDto.getDescription(), productDto.getNumberInStock());
        Optional<ShoppingCart> cartOptional = shoppingCommandRepository.findById(cartId);
        if (cartOptional.isPresent() && product != null) {
            ShoppingCart cart = cartOptional.get();
            cart.addToCart(product, quantity);
            sender.send(cart);
            shoppingCommandRepository.save(cart);
        }
//        else if (product != null) {
//            ShoppingCart cart = new ShoppingCart();
//            cart.setCartId(cartId);
//            cart.addToCart(product, quantity);
//            shoppingCommandRepository.save(cart);
//        }
    }

    public void removeProductFromCart(int cartId, int productId) {

        Optional<ShoppingCart> cartOptional = shoppingCommandRepository.findById(cartId);

        if (cartOptional.isPresent()) {
            Product product=null;
            ShoppingCart cart = cartOptional.get();
            Iterator<CartLine> iter = cart.getCartLineList().iterator();
            while (iter.hasNext()){
                CartLine cline = iter.next();
                if (cline.getProduct().getProductNumber() == productId){
                    product = new Product(cline.getProduct().getProductNumber(), cline.getProduct().getName(),cline.getProduct().getPrice(),
                            cline.getProduct().getDescription(), cline.getProduct().getNumberInStock());
                }
            }
            cart.removeFromCart(product);
           sender.send(cart);
            shoppingCommandRepository.save(cart);
        }
    }

    public void changeProductQuantity(int cartId, int quantity, ProductDTO productDTO) {

        Product product = new Product(productDTO.getProductNumber(), productDTO.getName(),productDTO.getPrice(),productDTO.getDescription(), productDTO.getNumberInStock());
        Optional<ShoppingCart> cartOptional = shoppingCommandRepository.findById(cartId);

        if (cartOptional.isPresent() && product != null) {
            ShoppingCart cart = cartOptional.get();
            Iterator<CartLine> iter = cart.getCartLineList().iterator();
            while (iter.hasNext()){
                CartLine cline = iter.next();
                if (cline.getProduct().getProductNumber() == productDTO.getProductNumber()){

                    cart.changeQuantity(product,quantity);
                    sender.send(cart);
                    shoppingCommandRepository.save(cart);
                }
            }

        }

    }
}
