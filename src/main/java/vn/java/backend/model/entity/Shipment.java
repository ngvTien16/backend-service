package vn.java.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;

    private String trackingNumber;
    private String status;
}
