package org.dutta.publish;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;

import org.dutta.model.Order;
import org.springframework.stereotype.Service;

@Service
public class PublishOrder {

    private QueueMessagingTemplate queueMessagingTemplate;

    public PublishOrder(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate=new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void publish(Order order) {
        System.out.println("Publishing order id:"+order.getOrderId());
        queueMessagingTemplate.convertAndSend("payment-updates",order);
    }
}
