package vn.java.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private String name ;
    private String description;
    private BigDecimal price;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "product")
    private List<ProductOption> options ;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

}
