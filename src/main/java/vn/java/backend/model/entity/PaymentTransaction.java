package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payment_transactions")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private BigDecimal amount;
    private String status;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}
