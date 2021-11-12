package shoppingcart.web;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import shoppingcart.domain.OrderLine;
import shoppingcart.domain.Product;
import shoppingcart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;


@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	CustomerFeignClient customerFeignClient;

	@Autowired
	ProductQuantityFeignClient productQuantityFeignClient;

	@FeignClient(name = "CustomerService")
	interface CustomerFeignClient {
		@PutMapping("/customers")
		public void sendEmail( @RequestBody OrderDTO order);
	}

	@FeignClient(name = "ProductService")
	interface ProductQuantityFeignClient {
		@PutMapping("/products")
		public void updateQuantity( @RequestBody OrderDTO order);
	}


	@GetMapping("/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable int orderId) {
		OrderDTO orderDTO = OrderAdapter.getOrderDTO(orderService.getOrder(orderId));
		if (orderDTO == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Order not exist"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody ShoppingCartDTO shoppingCart) {

		if ( shoppingCart ==null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(" / Shopping Cart is not Exist" ),
					HttpStatus.NOT_FOUND);
		}
		orderService.createOrder(shoppingCart);
		return new ResponseEntity<OrderDTO>(HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> addCustomerToOrder(@RequestBody OrderDTO orderDTO) {

		if ( orderDTO ==null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(" Order is not Exist " ),
					HttpStatus.NOT_FOUND);
		}

		orderService.updateOrder(orderDTO);
		return new ResponseEntity<OrderDTO>(HttpStatus.OK);
	}

	@PostMapping("/{orderId}")
	public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO, @PathVariable int orderId) {

		if ( orderDTO ==null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType(" / Order with order id " +orderDTO.getOrderId() + " is not Exist" ),
					HttpStatus.NOT_FOUND);
		}
		orderService.placeOrder(orderDTO, orderId);

		productQuantityFeignClient.updateQuantity(orderDTO);
//		customerFeignClient.sendEmail(orderDTO);

		return new ResponseEntity<OrderDTO>(HttpStatus.OK);
	}

}
