package org.paysdk.pay.controllers;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.models.User;
import org.paysdk.pay.models.UserResponse;
import org.paysdk.pay.services.PaymentService;
import org.paysdk.pay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.paysdk.pay.util.convertors.UserConvertor.*;

@RestController
@RequiredArgsConstructor
public class GameController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final PaymentService paymentService;

    @PostMapping("/start/{token}")
    public UserResponse start(@PathVariable String token){
        User user = userService.findByToken(token);
        return convert(user);
    }
}
