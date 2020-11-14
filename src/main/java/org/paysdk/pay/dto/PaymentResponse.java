package org.paysdk.pay.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

    private String username;

    private String description;

    private Double moneyAmount;
}
