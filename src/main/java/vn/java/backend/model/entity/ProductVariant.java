package vn.java.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_variants")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    private String sku;
    private BigDecimal price;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventory> inventories;

}
