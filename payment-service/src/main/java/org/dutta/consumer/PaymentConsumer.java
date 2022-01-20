package org.dutta.consumer;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.dutta.model.Order;
import org.dutta.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

PaymentService paymentService;

    public PaymentConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @SqsListener("payment-updates")
    public void consume(Order orderResponse)
    {
        System.out.println("Received order details for order id.."+orderResponse.getOrderId());
        paymentService.send(orderResponse);
    }
}
