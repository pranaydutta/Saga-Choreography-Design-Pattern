package org.dutta.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.dutta.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {


    @Autowired
    private DynamoDBMapper mapper;

//    public OrderRepository(DynamoDBMapper mapper) {
//        this.mapper = mapper;
//    }
//
//
//    public OrderRepository() {
    //}

    public Order saveOrder(Order order)
    {
        System.out.println("dynamoDBMapper :"+mapper);
        mapper.save(order);
        return order;
    }

    public Order getOrderById(String orderId) {
        Order order = null;
        try {
            order = mapper.load(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception returning empty order");
            return new Order();
        }

        return order;
    }
}
