package org.paysdk.pay.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String telegramId;

    private String merchantId;

    private String secretKey;
}
