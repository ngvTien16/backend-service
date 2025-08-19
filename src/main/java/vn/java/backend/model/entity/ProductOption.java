package vn.java.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_options")
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String name ;

    @OneToMany(mappedBy = "productOption" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProductVariant> variants;

}
