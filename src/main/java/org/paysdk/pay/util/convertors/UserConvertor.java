package org.paysdk.pay.util.convertors;

import org.paysdk.pay.models.User;
import org.paysdk.pay.models.UserRequest;
import org.paysdk.pay.models.UserResponse;

public class UserConvertor {

    public static User convert(UserRequest userRequest) {
        return User.builder()
                .telegramId(userRequest.getTelegramId())
                .merchantId(userRequest.getMerchantId())
                .secretKey(userRequest.getSecretKey())
                .build();
    }

    public static UserResponse convert(User user) {
        return UserResponse.builder()
                .merchantId(user.getMerchantId())
                .secretKey(user.getSecretKey())
                .build();
    }
}
