package org.dutta.publish;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.dutta.model.OrderStatus;
import org.dutta.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentPublisher {

    private QueueMessagingTemplate queueMessagingTemplate;

    public PaymentPublisher(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate=new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void publish(Payment payment)
    {
        System.out.println("Publishing order id:"+payment.getOrderId());
        queueMessagingTemplate.convertAndSend("restraunt-updates",payment);
    }

    public void publish(OrderStatus orderStatus) {
        System.out.println("Publishing OrderStatus to order id:"+orderStatus.getOrderId());
        queueMessagingTemplate.convertAndSend("order-updates",orderStatus);

    }
}
