package com.balkis.delivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class OrdreProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordre_id")
    private Ordre ordre;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;



    // Constructeurs et autres méthodes

    public OrdreProduct() {
        // Constructeur par défaut
    }

    public OrdreProduct(Ordre ordre, Product product, Integer quantity) {
        this.ordre = ordre;
        this.product = product;

    }

    // Getters et setters
}
