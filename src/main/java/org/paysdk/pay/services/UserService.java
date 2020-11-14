package org.paysdk.pay.services;

import org.paysdk.pay.models.User;

public interface UserService {

    User save(User user);

    User findByTelegramId(String telegramId);
}
