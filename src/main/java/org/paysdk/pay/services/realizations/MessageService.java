package org.paysdk.pay.services.realizations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.paysdk.pay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private ObjectMapper objectMapper;

    public User extractUser(String message) {
        // TODO extract user from string
        return objectMapper.convertValue(message, User.class);
    }
}
