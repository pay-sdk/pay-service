package org.paysdk.pay.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {

    private String name;

    private String token;
}
