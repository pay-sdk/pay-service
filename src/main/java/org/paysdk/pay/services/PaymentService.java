package org.paysdk.pay.services;

import org.paysdk.pay.models.Payment;

public interface PaymentService {

    Payment save(Payment payment);
}
