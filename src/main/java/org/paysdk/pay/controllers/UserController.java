package org.paysdk.pay.controllers;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.models.User;
import org.paysdk.pay.models.UserRequest;
import org.paysdk.pay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.paysdk.pay.util.convertors.UserConvertor.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping()
    private String register(@RequestBody UserRequest userRequest) {
        User savedUser = userService.save(convert(userRequest));
        return savedUser.getToken();
    }
}
