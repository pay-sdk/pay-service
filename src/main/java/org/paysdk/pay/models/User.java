package org.paysdk.pay.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String telegramId;

    private String merchnedId;

    private String secretKey;

    @OneToMany
    private List<Payment> payment;
}
