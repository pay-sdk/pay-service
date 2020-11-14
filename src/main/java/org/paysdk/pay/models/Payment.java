package org.paysdk.pay.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Data
@Builder
@Entity
public class Payment {

    private Long id;

    private String username;

    private String description;

    private Double amount;

    private Date data;

    private PaymentStatus paymentStatus;

    @ManyToOne
    private User user;
}
