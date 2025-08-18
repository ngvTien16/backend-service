package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shop_orders")
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private BigDecimal totalAmount;
    private String status;

    @OneToMany(mappedBy = "shopOrder")
    private List<OrderItem> items;

}
