package shoppingcart.service;

import shoppingcart.domain.Order;
import shoppingcart.domain.OrderLine;

public class OrderAdapter {
    public static Order getOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomer(CustomerAdopter.getCustomer(orderDTO.getCustomer()));
        order.setStatus(orderDTO.getStatus());
        for (OrderLineDTO orderLineDTO : orderDTO.getOrderLineList()) {
            order.addToOrdering(ProductAdapter.getProduct(orderLineDTO.getProduct()), orderLineDTO.getQuantity());
        }

        return order;
    }

    public static OrderDTO getOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setStatus(order.getStatus());
        for (OrderLine orderLine : order.getOrderLineList()) {
            OrderLineDTO orderLineDTO = new OrderLineDTO();
            orderLineDTO.setQuantity(orderLine.getQuantity());
            orderLineDTO.setProduct(ProductAdapter.getProductDTO(orderLine.getProduct()));
            orderDTO.addOrderLine(orderLineDTO);
        }
        return orderDTO;
    }

    public static OrderDTO getOrderCustomerDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomer(CustomerAdopter.getCustomerDTO(order.getCustomer()));
        orderDTO.setStatus(order.getStatus());
        for (OrderLine orderLine : order.getOrderLineList()) {
            OrderLineDTO orderLineDTO = new OrderLineDTO();
            orderLineDTO.setQuantity(orderLine.getQuantity());
            orderLineDTO.setProduct(ProductAdapter.getProductDTO(orderLine.getProduct()));
            orderDTO.addOrderLine(orderLineDTO);
        }
        return orderDTO;
    }
}
