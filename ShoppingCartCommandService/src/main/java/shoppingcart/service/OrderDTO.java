package shoppingcart.service;

import java.util.ArrayList;

public class OrderDTO {
    private int orderNumber;
    private ArrayList<OrderLineDTO> orderLineList = new ArrayList<OrderLineDTO>();

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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
