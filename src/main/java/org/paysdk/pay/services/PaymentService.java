package org.paysdk.pay.services;

import org.paysdk.pay.models.Payment;

import java.util.List;

public interface PaymentService {

    Payment save(Payment payment);

    List<Payment> getAll();
}
