package org.dutta.consumer;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.dutta.model.Order;
import org.dutta.model.OrderStatus;
import org.dutta.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderConsumer {

    private OrderRepository orderRepository;

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @SqsListener("order-updates")
    public void consume(OrderStatus orderStatus)
    {
        System.out.println("Received Order status for orderid :"+orderStatus.getOrderId());
       Order order= orderRepository.getOrderById(orderStatus.getOrderId());
       order.setOrderStatus(orderStatus.getOrderState().name());
       order.setUpdatedTime(new Timestamp(System.currentTimeMillis()).toString());
       orderRepository.saveOrder(order);
    }
}
