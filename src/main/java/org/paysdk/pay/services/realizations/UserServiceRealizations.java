package org.paysdk.pay.services.realizations;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.models.User;
import org.paysdk.pay.repositories.UserRepository;
import org.paysdk.pay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceRealizations implements UserService {

    @Autowired
    private final UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return null;
    }
}
