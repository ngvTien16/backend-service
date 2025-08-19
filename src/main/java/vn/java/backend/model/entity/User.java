package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<Shop> shops;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Cart> carts;

}
