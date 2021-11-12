package shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.data.ProductRepository;
import shoppingcart.domain.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Optional<Product> removeProduct(int productId) {
        Optional<Product> product1 = productRepository.findById(productId);
        if (product1.isPresent()) {
            productRepository.deleteById(productId);
        }
        return product1;
    }

    public void updateQuantity(Product product, int quantity) {
        Optional<Product> product1 = productRepository.findById(product.getProductNumber());
        int stock = product1.get().getNumberInStock() - quantity;
        product.setNumberInStock(stock);
        productRepository.save(product);

    }

    public void updateProduct(Product product, int productId){
        Optional<Product> product1 = productRepository.findById(productId);
        if(product1.isPresent()){
            productRepository.save(product);
        }
    }

    public Product getProduct(int productId){
        Product product = productRepository.findById(productId).orElse(null);
        return product;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
