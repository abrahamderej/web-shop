package shoppingcart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shoppingcart.domain.ShoppingCart;

@Service
public class Receiver {
	@Autowired
	ShoppingCartQueryService shoppingCartQueryService;

    @KafkaListener(topics = "shoppingcart")
    public void receive(@Payload ShoppingCart shoppingCart,
                        @Headers MessageHeaders headers) {
        System.out.println("received message="+ shoppingCart.toString());
        shoppingCartQueryService.addToCart(shoppingCart);
    }

}
