package org.paysdk.pay.services.realizations;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.models.User;
import org.paysdk.pay.repositories.UserRepository;
import org.paysdk.pay.services.UserService;
import org.paysdk.pay.util.RandomTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceRealizations implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        User savedUser;
        // update
        if ((savedUser = userRepository.findByTelegramId(user.getTelegramId())) != null) {
            savedUser.setMerchantId(user.getMerchantId());
            savedUser.setSecretKey(user.getSecretKey());
            savedUser.setTelegramId(user.getTelegramId());
            return userRepository.save(savedUser);
        }
        // save
        return userRepository.save(user);
    }

    @Override
    public User findByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }
}
