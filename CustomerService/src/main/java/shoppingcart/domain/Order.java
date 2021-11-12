package shoppingcart.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private long orderId;
    private Customer customer;
    private String status;
    private ArrayList<OrderLine> orderLineList = new ArrayList<>();

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
