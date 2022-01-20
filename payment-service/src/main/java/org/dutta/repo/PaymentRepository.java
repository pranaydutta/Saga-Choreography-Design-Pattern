package org.dutta.repo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.dutta.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

    private DynamoDBMapper dynamoDBMapper;

    public PaymentRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Payment save(Payment payment) {
        System.out.println("dynamoDBMapper"+dynamoDBMapper);
        dynamoDBMapper.save(payment);

        return payment;
    }
}
