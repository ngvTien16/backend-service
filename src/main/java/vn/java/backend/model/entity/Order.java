package vn.java.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    private BigDecimal totalAmount;
    private String status;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShopOrder> shopOrders;
}
