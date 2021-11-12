package shoppingcart.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcart.domain.ShoppingCart;

public interface ShoppingQueryRepository  extends MongoRepository<ShoppingCart, Integer> {
}
