package shoppingcart.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import shoppingcart.domain.Product;

public interface ProductRepository  extends MongoRepository<Product, Integer> {
}
