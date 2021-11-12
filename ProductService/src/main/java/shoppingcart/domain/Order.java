package shoppingcart.domain;

import java.util.ArrayList;

public class Order {

    ArrayList<OrderLine> orderLineList = new ArrayList<OrderLine>();
    private long orderId;
    private Customer customer;
    private String status;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public ArrayList<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(ArrayList<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", orderLineList=" + orderLineList +
                '}';
    }
}
