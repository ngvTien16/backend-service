package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product_options")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name ;

    @OneToMany(mappedBy = "productOption" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProductVariant> variants;

}
