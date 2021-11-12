package shoppingcart.web;

import shoppingcart.domain.Customer;
import shoppingcart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingcart.service.OrderDTO;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable int customerId){
         Customer customer = customerService.getCustomer(customerId);
        if(customer == null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Customer with id = " + customerId + "is not Exist"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        Customers customers =  new Customers(customerService.getAllCustomers());
        return new ResponseEntity<Customers>(customers, HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable int customerId) {
        customerService.updateCustomer(customer, customerId);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> removeCustomer( @PathVariable int customerId) {
        Customer customer =customerService.getCustomer(customerId);
        if(customer == null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Customer with id = " + customerId + "is not Exist"),
                    HttpStatus.NOT_FOUND);
        }
        customerService.removeCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/customers")
    public void sendEmail(@RequestBody OrderDTO orderDTO) {
        System.out.println(orderDTO.getCustomer().getFirstName()
        + " Your Order " + "Product: " + orderDTO.getOrderLineList().get(1).getProduct().getDescription()
        +" Successfully");
    }

}
