package vn.java.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "voucher_usages")
public class VoucherUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = " user_id")
    private User user;

    @Column(name = "used_at")
    private Instant usedAt = Instant.now();
}
