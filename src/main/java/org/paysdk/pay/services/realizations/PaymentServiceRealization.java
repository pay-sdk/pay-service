package org.paysdk.pay.services.realizations;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.models.Payment;
import org.paysdk.pay.repositories.PaymentRepository;
import org.paysdk.pay.repositories.UserRepository;
import org.paysdk.pay.services.PaymentService;
import org.paysdk.pay.util.NotifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceRealization implements PaymentService {

    @Autowired
    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    @Override
    public Payment save(Payment payment) {

        String tid = payment.getProject().getUser().getTelegramId();

        //asyncNotification(payment, tid);

        return paymentRepository.save(payment);
    }

    @Async
    public void asyncNotification(Payment payment, String tid) {
        NotifyUtil.sendNotification(payment, tid);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }
}
