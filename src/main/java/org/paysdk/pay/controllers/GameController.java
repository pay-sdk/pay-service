package org.paysdk.pay.controllers;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.dto.PaymentRequest;
import org.paysdk.pay.dto.PaymentResponse;
import org.paysdk.pay.models.Payment;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;
import org.paysdk.pay.dto.UserResponse;
import org.paysdk.pay.services.PaymentService;
import org.paysdk.pay.services.ProjectService;
import org.paysdk.pay.services.UserService;
import org.paysdk.pay.util.convertors.PaymentConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.paysdk.pay.util.convertors.UserConvertor.*;
import static org.paysdk.pay.util.convertors.PaymentConvertor.*;

@RestController
@RequiredArgsConstructor
public class GameController {

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final PaymentService paymentService;

    @GetMapping("/start/{token}")
    public UserResponse start(@PathVariable String token){
        Project project = projectService.findByToken(token);
        if (project == null) {
            return null;
        }
        return convert(project.getUser());
    }

    @PostMapping("/payment")
    public PaymentResponse savePayment(@RequestBody PaymentRequest paymentRequest) {
        Project project = projectService.findByToken(paymentRequest.getToken());
        if (project == null) {
            return null;
        }
        Payment payment = convert(paymentRequest);
        payment.setProject(project);
        Payment savedPayment = paymentService.save(payment);
        return convert(savedPayment);
    }

    @GetMapping("/payments")
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentService.getAll();
        return payments.stream().map(PaymentConvertor::convert).collect(Collectors.toList());
    }
}
