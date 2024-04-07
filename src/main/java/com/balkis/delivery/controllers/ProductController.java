package com.balkis.delivery.controllers;


import com.balkis.delivery.models.Product;
import com.balkis.delivery.models.Userproduct;
import com.balkis.delivery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.ok(addedProduct);
    }

    @GetMapping("/all")

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    // Mettez à jour votre méthode de mise à jour dans le contrôleur
    @PutMapping("/producted/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        // Vérifiez si le produit avec l'ID donné existe
        Optional<Product> existingProductOptional = productService.getProductById(productId);

        if (existingProductOptional.isPresent()) {
            // Mettez à jour les champs du produit existant avec les valeurs fournies
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            // Continuez de la même manière pour les autres champs

            // Appelez le service pour sauvegarder les modifications
            productService.saveProduct(existingProduct);

            // Retournez le produit mis à jour
            return ResponseEntity.ok(existingProduct);
        } else {
            // Si le produit avec l'ID donné n'existe pas, renvoyez une réponse 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        // Check if the product with the given ID exists
        Optional<Product> existingProductOptional = productService.getProductById(productId);

        if (existingProductOptional.isPresent()) {
            // If the product exists, delete it
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            // If the product with the given ID does not exist, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
    }
}