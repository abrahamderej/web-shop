package shoppingcart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingcart.domain.Product;
import shoppingcart.service.OrderDTO;
import shoppingcart.service.ProductAdapter;
import shoppingcart.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger =
			LoggerFactory.getLogger(ProductController.class.getName());
	@Autowired
	ProductService productService;

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable int productId){
		Product product = productService.getProduct(productId);
		if(product == null){
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product with id = " + productId + "is not Exist"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllProducts(){
		Products products =  new Products(productService.getAllProducts());
		return new ResponseEntity<Products>(products, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable int productId) {
		productService.updateProduct(product, productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> removeProduct( @PathVariable int productId) {
		Product customer =productService.getProduct(productId);
		if(customer == null){
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Product with id = " + productId + "is not Exist"),
					HttpStatus.NOT_FOUND);
		}
		productService.removeProduct(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public void updateQuantity(@RequestBody OrderDTO orderDTO) {
		productService.updateQuantity(ProductAdapter.getProduct(orderDTO.getOrderLineList().get(1).getProduct())
				, orderDTO.getOrderLineList().get(1).getQuantity());

	}

}
