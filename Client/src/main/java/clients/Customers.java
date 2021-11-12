package clients;

import java.util.Collection;

public class Customers {

    private Collection<CustomerDTO> customers;

    public Customers() {
    }

    public Customers(Collection<CustomerDTO> customers) {
        this.customers = customers;
    }

    public Collection<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<CustomerDTO> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customers=" + customers +
                '}';
    }
}
