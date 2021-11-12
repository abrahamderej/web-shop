package clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String serverUrl = "http://localhost:8080/";
//-----------------------------Customer Service Test ------------------------------------------------------
		AddressDTO address = new AddressDTO("Mainstreet 1", "Chicago", "57889");
		ContactDTO contact = new ContactDTO("+12346573","abrahamdereje@gmail.com");
		CustomerDTO customer = new CustomerDTO(1,"Frank Browns", "Lion King");
		customer.setAddress(address);
		customer.setContact(contact);

		CustomerDTO customer2 = new CustomerDTO(2,"John Doe", "Simba");
		AddressDTO address2 = new AddressDTO("1000 N 4th street", "Fairfield", "57577");
		ContactDTO contact2 = new ContactDTO("+641-46573","abiyekimu@gmail.com");
		customer2.setAddress(address2);
		customer2.setContact(contact2);
		// add customer 1
		restTemplate.postForLocation(serverUrl+"customers",customer);
		// add customer 2
		restTemplate.postForLocation(serverUrl+"customers", customer2);
		// get customer 1
		CustomerDTO getCustomerResult= restTemplate.getForObject(serverUrl+ "customers" +"/{customerId}", CustomerDTO.class, 1);
		System.out.println("----------- customer-----------------------");
		System.out.println(getCustomerResult.getFirstName()+" "+getCustomerResult.getLastName());
        // get all
		Customers customers= restTemplate.getForObject(serverUrl+"customers", Customers.class);
		System.out.println("----------- get all Customers-----------------------");
		System.out.println(customers);

		// delete 2
		restTemplate.delete(serverUrl+ "customers" +"/{customerId}", 2);

		// update 1
		customer.setFirstName("Abraham");
		restTemplate.put(serverUrl+ "customers" +"/{customerId}", customer, customer.getCustomerId());

		// get all
		customers = restTemplate.getForObject(serverUrl+"customers", Customers.class);
		System.out.println("----------- get All Customers-----------------------");
		System.out.println(customers);


//		//-----------------------------Product Service Test ------------------------------------------------------
//		ProductDTO product1 = new ProductDTO(1, "DVD", 3.0, "DVD All Inclusive", 12);
//		ProductDTO product2 = new ProductDTO(2, "T-shirt", 15.0, "Boys T-shirt",23);
//		ProductDTO product3 = new ProductDTO(3, "Pants", 45.0, "Men pants",13);
//
//		// add Products
//		restTemplate.postForLocation(serverUrl+"products",product1);
//		restTemplate.postForLocation(serverUrl+"products", product2);
//		restTemplate.postForLocation(serverUrl+"products", product3);
//
//		ProductDTO getProductResult= restTemplate.getForObject(serverUrl+"products"+"/{productId}", ProductDTO.class, 1);
//		System.out.println("----------- product-----------------------");
//		System.out.println(getProductResult.getName()+" "+getProductResult.getNumberInStock());
//		// get all
//		Products products= restTemplate.getForObject(serverUrl+"products", Products.class);
//		System.out.println("----------- get all products-----------------------");
//		System.out.println(products);
//
//		// delete product with product Number 2
//		restTemplate.delete(serverUrl+"products"+"/{productId}", 2);
//
//		// update product with product Number 1
//		product1.setNumberInStock(34);
//		restTemplate.put(serverUrl+"products"+"/{productId}", product1, product1.getProductNumber());
//
//		// get all
//		products = restTemplate.getForObject(serverUrl+"products", Products.class);
//		System.out.println("----------- get All products-----------------------");
//		System.out.println(products);
//
////-----------------------------Shopping Service Test ------------------------------------------------------
//		//get a product and add to the shopping cart
//		ProductDTO p1 = restTemplate.getForObject(serverUrl+"products"+"/{productId}", ProductDTO.class, 1);
//		restTemplate.postForLocation(serverUrl+"shoppingcarts" +"/{cartId}" + "/{quantity}", p1, 1,1);
//
//
//		p1 = restTemplate.getForObject(serverUrl+"products"+"/{productId}", ProductDTO.class,3);
//		restTemplate.postForLocation(serverUrl+"shoppingcarts" +"/{cartId}" + "/{quantity}", p1, 2,3);
//
//		//get the shopping cart
//		ShoppingCartDTO cart = restTemplate.getForObject(serverUrl+"shoppingcarts"+"/{cartId}", ShoppingCartDTO.class, 2);
//		System.out.println("\n-----Shoppingcart-------");
//		if (cart != null) cart.print();
//
//		// Add Product to Existing Shopping Cart
//		ProductDTO p3 = restTemplate.getForObject(serverUrl+"products"+"/{productId}", ProductDTO.class, 3);
//		CartLineDTO cartLineDTO = new CartLineDTO();
//		cartLineDTO.setProduct(p3);
//		cartLineDTO.setQuantity(5);
//		restTemplate.postForLocation(serverUrl+"shoppingcarts"+"/{cartId}", cartLineDTO, 1,7);
//
//		ShoppingCartDTO cart2 = restTemplate.getForObject(serverUrl+"shoppingcarts"+"/{cartId}", ShoppingCartDTO.class, 1);
//		System.out.println("\n-----Shoppingcart-------");
//		if (cart2 != null) cart2.print();
//
//		// Change Product Quantity in Shopping Cart
//		restTemplate.put(serverUrl+"shoppingcarts"+"/{cartId}" +"/{quantity}", p3, 1,8);
//
//
//		ShoppingCartDTO cart3 = restTemplate.getForObject(serverUrl+"shoppingcarts"+"/{cartId}", ShoppingCartDTO.class, 1);
//		System.out.println("\n-----Shoppingcart-------");
//		if (cart3 != null) cart3.print();
//
//		// Remove Product Shopping Cart
//		restTemplate.delete(serverUrl+"shoppingcarts"+"/{cartId}" + "/{productId}", 2, 3);
//
//		//Checkout
//		ShoppingCartDTO checkOutCart = restTemplate.getForObject(serverUrl+"shoppingcarts"+"/{cartId}", ShoppingCartDTO.class, 1);
//		restTemplate.postForLocation(serverUrl+"shoppingcarts" , checkOutCart);
//
//
//
//		//-----------------------------Order Service Test ------------------------------------------------------
//		CustomerDTO c1 = restTemplate.getForObject(serverUrl+"customers"+"/{customerId}", CustomerDTO.class, 1);
//
//		OrderDTO order = restTemplate.getForObject(serverUrl+"orders"+"/{orderId}", OrderDTO.class, 81);
//		order.setCustomer(c1);
//		restTemplate.put(serverUrl+"orders", order);
//
//		order.print();
//
//		// Place Order
//		restTemplate.postForLocation(serverUrl+"orders"+"/{orderId}" , order,81);




	}


	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
