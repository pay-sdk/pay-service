package org.paysdk.pay.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String merchantId;

    private String secretKey;
}
