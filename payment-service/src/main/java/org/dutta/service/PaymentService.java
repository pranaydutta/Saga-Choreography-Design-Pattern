package org.dutta.service;

import org.dutta.mapper.PaymentMapper;
import org.dutta.model.Order;
import org.dutta.model.OrderState;
import org.dutta.model.OrderStatus;
import org.dutta.model.Payment;
import org.dutta.publish.PaymentPublisher;
import org.dutta.repo.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

   private PaymentMapper paymentMapper;

    private PaymentRepository paymentRepository;
   private PaymentPublisher paymentPublisher;

    public PaymentService(PaymentMapper paymentMapper, PaymentRepository paymentRepository, PaymentPublisher paymentPublisher) {
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentPublisher = paymentPublisher;
    }



    public void send(Order orderResponse) {

Payment payment=paymentMapper.convert(orderResponse);
paymentRepository.save(payment);
paymentPublisher.publish(payment);
        OrderStatus orderStatus=OrderStatus.builder()
                        .orderState(OrderState.ORDER_PAID)
                                .orderId(orderResponse.getOrderId())
                                        .message("Sucessfully paid by "+payment.getPaymentMethod())
                                                .build();
paymentPublisher.publish(orderStatus);

    }
}

