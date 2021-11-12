package shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.data.OrderRepository;
import shoppingcart.domain.Order;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public void createOrder(ShoppingCartDTO shoppingCartDTO){
        Order order = new Order();
        order.createOrder(shoppingCartDTO);
        order.setOrderId(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));
        order.setStatus("hold");
        orderRepository.save(order);
    }

    public Order getOrder(int orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    public void updateOrder(OrderDTO orderDTO){

        Order order = OrderAdapter.getOrder(orderDTO);
        orderRepository.save(order);
    }

    public void placeOrder(OrderDTO orderDTO, int orderId){
        Order order = OrderAdapter.getOrder(orderDTO);
        order.setStatus("Confirmed");
        orderRepository.save(order);
    }

}
