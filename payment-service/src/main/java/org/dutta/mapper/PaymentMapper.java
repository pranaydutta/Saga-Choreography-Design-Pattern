package org.dutta.mapper;

import org.dutta.model.Order;
import org.dutta.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {


    public Payment convert(Order orderResponse) {

        return Payment.builder()
                .orderId(orderResponse.getOrderId())
                .sellerId(orderResponse.getSellerId())
                .customerId((orderResponse.getCustomerId()))
                .paymentMethod(orderResponse.getPaymentMethod())
                .paymentStatus("SUCCESS")
                .paymentStatusNotes("Sucessfully paid by :"+orderResponse.getPaymentMethod())
                .build();
    }
}
