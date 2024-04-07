package com.balkis.delivery.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Ordre {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    private String ref;


    private String description;

    private Boolean state;

    private Integer qte_totale;

    private Double price_total;

    @ManyToOne
    @JoinColumn(name = "driver")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @OneToMany(mappedBy = "ordre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdreProduct> ordreProducts = new ArrayList<>();

    // Constructeurs et autres méthodes

    public void addProduct(Product product, Integer quantity) {
        ordreProducts.add(new OrdreProduct(this, product, quantity));
    }

    // Constructeur par défaut
    public Ordre() {
        this.ordreProducts = new ArrayList<>();
    }

    public Ordre(String ref, String description, Boolean state, Integer qte_totale, Double price_total) {
        this.ref = ref;
        this.description = description;
        this.state = state;
        this.qte_totale = qte_totale;
        this.price_total = price_total;
        this.ordreProducts = new ArrayList<>();
    }

    // Getters et Setters
}
