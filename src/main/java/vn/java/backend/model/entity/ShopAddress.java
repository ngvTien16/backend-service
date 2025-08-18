package vn.java.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shop_addresses")
public class ShopAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private String addressLine ;
    private String city;
    private String country;
}

