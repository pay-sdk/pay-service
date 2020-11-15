package org.paysdk.pay.services.realizations;

import org.paysdk.pay.models.User;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public User extractUser(String message) {
        String[] strings = message.split("\n");
        if (strings.length != 2) {
            throw new IllegalArgumentException();
        }
        return User.builder()
                .merchantId(strings[0])
                .secretKey(strings[1])
                .build();
    }
}
