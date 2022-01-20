package org.dutta.service;

import com.dutta.shared.CreateOrderRequest;
import com.dutta.shared.GetOrderRequest;
import com.dutta.shared.OrderResponse;
import com.dutta.shared.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dutta.mapper.OrderMapper;
import org.dutta.model.Order;
import org.dutta.publish.PublishOrder;
import org.dutta.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PublishOrder publishOrder;

    @Override
    public void createOrder(CreateOrderRequest request, StreamObserver<OrderResponse> responseObserver) {

        Order order = orderMapper.convert(request);

        //Convert to DynamoDB pojo
        //persist to dynamodb
        //publish to SQS
        repository.saveOrder(order);

        publishOrder.publish(order);

        OrderResponse orderResponse = orderMapper.convert(order);
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();


    }

    @Override
    public void getOrder(GetOrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        Order order = repository.getOrderById(request.getOrderId());

        OrderResponse orderResponse = orderMapper.convert(order);
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();

    }
}
