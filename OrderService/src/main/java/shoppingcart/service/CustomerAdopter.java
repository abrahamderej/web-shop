package shoppingcart.service;


import shoppingcart.domain.Customer;

public class CustomerAdopter {

    public static Customer getCustomer(CustomerDTO customerDto){
        Customer customer = new Customer(
                customerDto.getCustomerId(),
                customerDto.getFirstName(),
                customerDto.getLastName());

        return customer;
    }

    public static CustomerDTO getCustomerDTO(Customer customer){
        CustomerDTO customerDto = new CustomerDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName()
        );

        return customerDto;
    }
}
