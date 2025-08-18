package vn.java.backend.model.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    private String name ;
    private String description;

    @Column(name = "created_at")
    private Instant createdAt =Instant.now();

    @OneToMany(mappedBy = "shop")
    private List<Product> products;


}
