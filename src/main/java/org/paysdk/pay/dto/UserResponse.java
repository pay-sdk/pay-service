package org.paysdk.pay.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long userId;

    private String merchantId;

    private String secretKey;
}
