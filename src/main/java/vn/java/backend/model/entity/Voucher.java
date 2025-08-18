package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String code ;
    private BigDecimal discount;

    private Instant validFrom ;
    private Instant validTo;

    @OneToMany(mappedBy = "voucher")
    private List<VoucherUsage> usages;
}
