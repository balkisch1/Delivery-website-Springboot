package com.balkis.delivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Userproduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "rating")
    private double rating;
    @Column(name = "image")
    private String image;
    @Column(name = "promotion")
    private String promotion;
    @Column(name = "description")
    private String description;
    @Column(name = "ingredients")
    @ElementCollection
    private List<String> ingredients;
    @Column(name = "category")
    private String category;
    @Column(name = "deliveryTime")
    private String deliveryTime;

    @Column(name = "average_rating", nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double averageRating = 0.0;
    private Double userRatings;

    // Default constructor (required by JPA)
    public Userproduct() {
    }

    // Parameterized constructor
    public Userproduct(String name, double price, double rating, String image, String promotion, String description,  List<String> ingredients, String category, String deliveryTime, Double averageRating, Double userRatings) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.promotion = promotion;
        this.description = description;
        this.ingredients = ingredients;
        this.category = category;
        this.deliveryTime = deliveryTime;
        this.averageRating = averageRating;
        this.userRatings = userRatings;
    }



    // getters and setters
}
