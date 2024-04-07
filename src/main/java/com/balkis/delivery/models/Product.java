package com.balkis.delivery.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@Entity

@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double price;


    @Column(name = "promotion")
    private Integer promotion;

    @Column(name = "delivery_time")
    private Integer deliveryTime;

    @Column(name = "average_rating")
    private Double averageRating;
    @Column(name = "image")
    private String image;


    @ManyToOne
    @JoinColumn(name = "provider")
    private Provider provider;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdreProduct> ordreProducts;

    // Constructeurs et autres méthodes

    public void addOrdre(Ordre ordre, Integer quantity) {
        if (ordreProducts == null) {
            ordreProducts = new ArrayList<>();
        }
        ordreProducts.add(new OrdreProduct(ordre, this, quantity));
    }



    public Product() {
        // Constructeur par défaut
    }

    public Product(Double price, String image, String name,String description, Integer deliveryTime, Integer   promotion, Double averageRating) {
        this.averageRating = averageRating;
        this.price = price;
        this.name = name;
        this.deliveryTime = deliveryTime;
        this.promotion = promotion;
        this.description = description;
        this.image = image;
    }
}
