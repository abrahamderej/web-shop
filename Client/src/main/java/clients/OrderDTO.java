package clients;

import java.util.ArrayList;

public class OrderDTO {
    private long orderId;
    private CustomerDTO customer;
    private ArrayList<OrderLineDTO> orderLineList = new ArrayList<OrderLineDTO>();

    public void print() {
        System.out.println("Content of the Order Detail:");
        System.out.println("Customer: " + customer);
        for (OrderLineDTO orderLine : orderLineList) {

            System.out.println("Product Quantity: " + orderLine.getQuantity() + " "
                    + orderLine.getProduct().getProductNumber() + " "
                    + orderLine.getProduct().getDescription() + " "
                    + orderLine.getProduct().getPrice());
        }
        System.out.println();
        System.out.println();

    }
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ArrayList<OrderLineDTO> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(ArrayList<OrderLineDTO> orderLineList) {
        this.orderLineList = orderLineList;
    }
    public void addOrderLine(OrderLineDTO orderLine) {
        orderLineList.add(orderLine);
    }
}
