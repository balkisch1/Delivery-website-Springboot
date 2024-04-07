package com.balkis.delivery.controllers;

import com.balkis.delivery.models.Ordre;
import com.balkis.delivery.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Ordre> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Ordre> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/saveOrder")
    public Ordre saveOrder(@RequestBody Ordre order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Map<String, String>> placeOrder(@RequestBody Ordre order) {
        // Ajoutez ceci pour vérifier le contenu de la commande reçue
        System.out.println("Received order: " + order);

        boolean orderPlaced = orderService.placeOrder(order);

        if (orderPlaced) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Commande placée avec succès");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody Ordre order) {
        boolean orderCreated = orderService.createOrder(order);

        if (orderCreated) {
            return ResponseEntity.ok("Order created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order");
        }
    }

}
