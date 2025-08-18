package vn.java.backend.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    private String name ;
    private String description;

    @OneToMany(mappedBy = "parent")
    private List<Category> subCategories;

}
