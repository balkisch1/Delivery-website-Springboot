package com.balkis.delivery.controllers;

import com.balkis.delivery.models.Userproduct;
import com.balkis.delivery.services.UserproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userproducts")
public class UserproductController {
    private final UserproductService userproductService;

    @Autowired
    public UserproductController(UserproductService userproductService) {
        this.userproductService = userproductService;
    }

    @PostMapping("/addproducts")
    public Userproduct addUserProduct(@RequestBody Userproduct userproduct) {

        return userproductService.addUserProduct(userproduct);
    }

    // Mettez à jour votre méthode de mise à jour dans le contrôleur
    @PutMapping("/products/{productId}")
    public ResponseEntity<Userproduct> updateProduct(@PathVariable Long productId, @RequestBody Userproduct updatedProduct) {
        // Vérifiez si le produit avec l'ID donné existe
        Optional<Userproduct> existingProductOptional = userproductService.getUserProductById(productId);

        if (existingProductOptional.isPresent()) {
            // Mettez à jour les champs du produit existant avec les valeurs fournies
            Userproduct existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            // Continuez de la même manière pour les autres champs

            // Appelez le service pour sauvegarder les modifications
            userproductService.saveUserProduct(existingProduct);

            // Retournez le produit mis à jour
            return ResponseEntity.ok(existingProduct);
        } else {
            // Si le produit avec l'ID donné n'existe pas, renvoyez une réponse 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteUserProduct(@PathVariable Long productId) {
        // Check if the product with the given ID exists
        Optional<Userproduct> existingProductOptional = userproductService.getUserProductById(productId);

        if (existingProductOptional.isPresent()) {
            // If the product exists, delete it
            userproductService.deleteUserProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            // If the product with the given ID does not exist, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public List<Userproduct> getAllUserProducts() {
        return userproductService.getAllUserProducts();
    }

    @GetMapping("/{id}")
    public Optional<Userproduct> getUserProductById(@PathVariable Long id) {
        return userproductService.getUserProductById(id);
    }

    // Additional endpoints for CRUD operations
}
