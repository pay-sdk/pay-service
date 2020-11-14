package org.paysdk.pay.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String description;

    private Double moneyAmount;

    @Type(type="date")
    private Date purchaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @PrePersist
    void purchaseDate() {
        this.purchaseDate = new Date();
    }
}
