package vn.java.backend.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shops",
        uniqueConstraints = {
                @UniqueConstraint(name = "shop_name", columnNames = "name"),
                @UniqueConstraint(name= " shop_owner", columnNames = "owner_id")
        }
)
@NoArgsConstructor@AllArgsConstructor@Builder
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Column(name = "created_at")
    private Instant createdAt =Instant.now();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;


}
