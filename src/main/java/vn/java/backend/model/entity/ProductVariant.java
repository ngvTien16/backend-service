package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variants")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    private String sku;
    private BigDecimal price;

    @OneToMany(mappedBy = "productVariant")
    private Inventory inventory;

}
