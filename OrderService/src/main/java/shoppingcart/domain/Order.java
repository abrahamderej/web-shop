package shoppingcart.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import shoppingcart.service.CartLineDTO;
import shoppingcart.service.ShoppingCartDTO;

import java.util.ArrayList;
import java.util.Iterator;

@Document
public class Order {
    @Transient
    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private long orderId;
    private Customer customer;
    private String status;

    ArrayList<OrderLine> orderLineList = new ArrayList<OrderLine>();


    public void createOrder(ShoppingCartDTO shoppingCartDTO) {

        Iterator<CartLineDTO> iter = shoppingCartDTO.getCartlineList().iterator();
        Product product=null;
        while (iter.hasNext()){
            CartLineDTO cline = iter.next();
            product = new Product(cline.getProduct().getProductNumber(), cline.getProduct().getName(),cline.getProduct().getPrice(),
                    cline.getProduct().getDescription(), cline.getProduct().getNumberInStock());

            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(cline.getQuantity());

            orderLineList.add(orderLine);
        }
    }

    public void addToOrdering(Product product, int quantity) {
        for (OrderLine cline : orderLineList) {
            if(cline.getProduct().getProductNumber()== product.getProductNumber()) {
                cline.setQuantity(cline.getQuantity()+quantity);
                return;
            }
        }
        OrderLine cline = new OrderLine();
        cline.setProduct(product);
        cline.setQuantity(quantity);
        orderLineList.add(cline);
    }

    public void changeQuantity(Product product, int quantity) {
        for (OrderLine cline : orderLineList) {
            if(cline.getProduct().getProductNumber()== product.getProductNumber()) {
                cline.setQuantity(quantity);
                return;
            }
        }
    }


    public void print() {
        System.out.println("Content of the shoppingcart:");
        for (OrderLine cline : orderLineList) {
            System.out.println(cline.getQuantity() + " "
                    + cline.getProduct().getProductNumber() + " "
                    + cline.getProduct().getDescription() + " "
                    + cline.getProduct().getPrice());
        }
        System.out.println("Total price ="+getTotalPrice());
    }

    public double getTotalPrice(){
        double totalPrice = 0.0;
        for (OrderLine cline : orderLineList) {
            totalPrice=totalPrice+(cline.getProduct().getPrice() * cline.getQuantity());
        }
        return totalPrice;
    }


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
