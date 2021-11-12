package shoppingcart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingcart.service.*;


@RestController
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {


    @Autowired
    ShoppingCommandService shoppingService;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    OrderFeignClient orderFeignClient;

    @FeignClient(name = "ProductService")
    interface ProductFeignClient {
        @GetMapping("/products/{productId}")
        public ProductDTO getProduct(@PathVariable("productId") int productId);
    }

    @FeignClient(name = "OrderService")
    interface OrderFeignClient {
        @PostMapping("/orders")
        public OrderDTO createOrder(@RequestBody ShoppingCartDTO shoppingCartDTO);
    }

    @PostMapping(value = "/{cartId}/{quantity}")
    public ResponseEntity<?> addToCart(@PathVariable int cartId, @PathVariable int quantity, @RequestBody ProductDTO productDto) {
        shoppingService.addToCart(cartId, productDto, quantity);

        return new ResponseEntity<ShoppingCartDTO>(HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable int cartId) {
        ShoppingCartDTO cart = shoppingService.getCart(cartId);
        return new ResponseEntity<ShoppingCartDTO>(cart, HttpStatus.OK);
    }


    @PostMapping("/{cartId}")
    public ResponseEntity<?> addProduct(@RequestBody CartLineDTO cartLineDTO, @PathVariable int cartId) {

        ProductDTO product = productFeignClient.getProduct(cartLineDTO.getProduct().getProductNumber());
        ShoppingCartDTO cart = shoppingService.getCart(cartId);

        if (product.getNumberInStock() < cartLineDTO.getQuantity() || cart ==null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Number of Product in Stock is = " + product.getNumberInStock() +" / Shopping Cart with id = " + cart + "is not Exist" ),
                    HttpStatus.NOT_FOUND);
        }
        shoppingService.addProductToCart(cartId, cartLineDTO.getProduct(), cartLineDTO.getQuantity());
        return new ResponseEntity<CartLineDTO>(cartLineDTO, HttpStatus.OK);
    }

    @PutMapping("/{cartId}/{quantity}")
    public ResponseEntity<?> changeQuantity(@PathVariable int cartId, @PathVariable int quantity, @RequestBody ProductDTO productDTO) {
        ProductDTO product = productFeignClient.getProduct(productDTO.getProductNumber());
        ShoppingCartDTO cart = shoppingService.getCart(cartId);

        if (product.getNumberInStock() < quantity || cart ==null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Number of Product in Stock is = " + product.getNumberInStock() +" / Shopping Cart with id = " + cart + "is not Exist" ),
                    HttpStatus.NOT_FOUND);
        }
        shoppingService.changeProductQuantity(cartId, quantity, productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable int cartId, @PathVariable int productId) {
        ShoppingCartDTO cart = shoppingService.getCart(cartId);
        if (cart == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Shopping Cart with id = " + cart + "is not Exist"),
                    HttpStatus.NOT_FOUND);
        }
        shoppingService.removeProductFromCart(cartId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> checkOut( @RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartDTO cart = shoppingService.getCart(shoppingCartDTO.getCartid());
        if (cart ==null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType(" / Shopping Cart with id = " + cart + "is not Exist" ),
                    HttpStatus.NOT_FOUND);
        }
        orderFeignClient.createOrder(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
