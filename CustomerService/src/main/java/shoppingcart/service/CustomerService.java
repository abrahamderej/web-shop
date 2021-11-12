package shoppingcart.service;

import shoppingcart.data.CustomerRepository;
import shoppingcart.domain.Customer;
import shoppingcart.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public Optional<Customer> removeCustomer(int customerId) {
        Optional<Customer> customer1 = customerRepository.findById(customerId);
        if (customer1.isPresent()) {
            customerRepository.deleteById(customerId);
        }
        return customer1;
    }

    public void updateCustomer(Customer customer, int customerId){
        Optional<Customer> customer1 = customerRepository.findById(customerId);
        if(customer1.isPresent()){
            customerRepository.save(customer);
        }
    }

    public Customer getCustomer(int customerId){
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return customer;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void SendEmail(Order order){
        System.out.println(order);
    }

}
