package org.paysdk.pay.repositories;

import org.paysdk.pay.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
