package shoppingcart.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcart.domain.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {
}
