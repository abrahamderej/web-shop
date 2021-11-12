package shoppingcart.service;

import java.util.ArrayList;

public class OrderDTO {

    private long orderId;
    private CustomerDTO customer;
    private String status;
    private ArrayList<OrderLineDTO> orderLineList = new ArrayList<OrderLineDTO>();

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
