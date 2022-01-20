package org.dutta.mapper;

import com.dutta.shared.CreateOrderRequest;
import com.dutta.shared.OrderResponse;
import org.dutta.entity.Order;
import org.dutta.model.Product;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderMapper {


    public Order convert(CreateOrderRequest request) {

        Order order = new Order();
        order.setOrderStatus("ORDER_CREATED");
        order.setCustomerId(request.getCustomerId());
        order.setSellerId(request.getSellerId());
        Product product = new Product();
        product.setProductId(request.getProduct().getProductId());
        product.setProductName(request.getProduct().getProductName());
        order.setProduct(product);
        order.setDeliveryLocation(request.getDeliverLocation());
        order.setPaymentMethod(request.getPaymentMethod().toString());
        String time = new Timestamp(System.currentTimeMillis()).toString();
        order.setCreatedTime(time);
        order.setUpdatedTime(time);
        return order;
    }

    public OrderResponse convert(Order order) {
        com.dutta.shared.Product item = com.dutta.shared.Product.newBuilder()
                .setProductId(order.getProduct().getProductId())
                .setProductName(order.getProduct().getProductName())
                .build();
        OrderResponse orderResponse = OrderResponse.newBuilder()
                .setCustomerId(order.getCustomerId())
                .setDeliveryLocation(order.getDeliveryLocation())
                .setPaymentMethod(order.getPaymentMethod())
                .setOrderStatus(order.getOrderStatus())
                .setSellerId(order.getSellerId())
                .setOrderId(order.getOrderId())
                .setProduct(item)
                .setCreatedTime(order.getCreatedTime())
                .setUpdatedTime(order.getUpdatedTime())
                .build();
        return orderResponse;
    }


}
