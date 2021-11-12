package shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingcart.domain.ShoppingCart;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, ShoppingCart> kafkaTemplate;

    @Value("${app.topic.shoppingcart}")
    private String topic;
    public void send( ShoppingCart shoppingCart){
        kafkaTemplate.send(topic, shoppingCart);
    }
}
