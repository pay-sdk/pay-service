package org.paysdk.pay.controllers;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.dto.PaymentRequest;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;
import org.paysdk.pay.dto.UserResponse;
import org.paysdk.pay.services.PaymentService;
import org.paysdk.pay.services.ProjectService;
import org.paysdk.pay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.paysdk.pay.util.convertors.UserConvertor.*;

@RestController
@RequiredArgsConstructor
public class GameController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ProjectService projectService;

    @Autowired
    private final PaymentService paymentService;

    @GetMapping("/start/{token}")
    public UserResponse start(@PathVariable String token){
        Project project = projectService.findByToken(token);
        return convert(project.getUser());
    }

    @PostMapping("/payment")
    public void savePayment(@RequestBody PaymentRequest paymentRequest) {
    }
}
