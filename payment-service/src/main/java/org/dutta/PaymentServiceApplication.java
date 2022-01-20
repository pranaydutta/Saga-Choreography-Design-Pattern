package org.dutta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {


    public static void main(String[] args) {
        try {
            SpringApplication.run(PaymentServiceApplication.class,args);
        }
        catch (Throwable throwable)
        {
            throw throwable;
        }

    }
}
