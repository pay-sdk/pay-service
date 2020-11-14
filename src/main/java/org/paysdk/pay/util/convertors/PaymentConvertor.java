package org.paysdk.pay.util.convertors;

import org.paysdk.pay.dto.PaymentRequest;
import org.paysdk.pay.dto.PaymentResponse;
import org.paysdk.pay.models.Payment;

public class PaymentConvertor {

    public static Payment convert(PaymentRequest paymentRequest) {
        return Payment.builder()
                .username(paymentRequest.getUsername())
                .description(paymentRequest.getDescription())
                .moneyAmount(paymentRequest.getMoneyAmount())
                .build();
    }

    public static PaymentResponse convert(Payment payment) {
        return PaymentResponse.builder()
                .username(payment.getUsername())
                .description(payment.getDescription())
                .moneyAmount(payment.getMoneyAmount())
                .build();
    }
}
