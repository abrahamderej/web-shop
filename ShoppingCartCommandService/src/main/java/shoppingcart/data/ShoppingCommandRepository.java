package shoppingcart.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcart.domain.ShoppingCart;

public interface ShoppingCommandRepository extends MongoRepository<ShoppingCart, Integer> {
}
