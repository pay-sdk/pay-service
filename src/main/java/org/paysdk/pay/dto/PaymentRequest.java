package org.paysdk.pay.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {

    private String token;

    private String username;

    private String description;

    private double moneyAmount;
}
