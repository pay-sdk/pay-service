package org.paysdk.pay.services.realizations;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.models.User;
import org.paysdk.pay.repositories.UserRepository;
import org.paysdk.pay.services.UserService;
import org.paysdk.pay.util.convertors.RandomTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceRealizations implements UserService {

    @Autowired
    private final UserRepository userRepository;

    private Random random = new Random();

    @Override
    public User save(User user) {

        if (user.getToken() == null) {
            user.setToken(RandomTokenGenerator.nextString());
        }

        return userRepository.save(user);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
