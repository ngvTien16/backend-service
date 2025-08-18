package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "return_requests")
public class ReturnRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    private String reason;
    private String status;

    @Column(name = "created_at")
    private Instant createdAt= Instant.now();
}
