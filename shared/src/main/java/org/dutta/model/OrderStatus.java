package org.dutta.model;


import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderStatus {

    private String orderId;
    private OrderState orderState;
    private String message;


}
