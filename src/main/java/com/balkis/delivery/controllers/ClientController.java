package com.balkis.delivery.controllers;


import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.User;
import com.balkis.delivery.security.JwtUtils;
import com.balkis.delivery.services.ClientService;

import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    private JwtUtils jwtUtils;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);

        String successMessage = "Client added successfully";

        // Créez un Map pour représenter la réponse
        Map<String, Object> response = new HashMap<>();
        response.put("message", successMessage);
        response.put("client", savedClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }




    // ClientController.java

    @PostMapping("/register")
    public ResponseEntity<Object> registerClient(@RequestBody Client client) {
        // Common user registration logic
        clientService.registerClient(client);

        String successMessage = "Client registered successfully";

        Map<String, Object> clientResponse = new HashMap<>();
        clientResponse.put("message", successMessage);
        clientResponse.put("client", getClientResponse(client));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }

    // Helper method to create a client-specific response
    private Map<String, Object> getClientResponse(Client client) {
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("firstName", client.getFirstName());
        clientMap.put("lastName", client.getLastName());
        clientMap.put("phone", client.getPhone());
        clientMap.put("username", client.getUsername());
        clientMap.put("email", client.getEmail());
        clientMap.put("localisation", client.getLocalisation());

        return clientMap;
    }

    // Modify your login endpoint in UserController
    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody Client loginClient) {
        try {
            // Retrieve the user by email
            Client client = clientService.findClientByEmail(loginClient.getEmail());

            if (client != null && client.checkPassword(loginClient.getPassword())) {
                String token = jwtUtils.generateToken(client.getEmail());

                // You might want to include the token in the response
                Map<String, String> response = new HashMap<>();
                response.put("token", token);

                return ResponseEntity.ok().body(response);
            } else {
                // Authentication failed
                return ResponseEntity.status(401).body("{\"error\": \"Invalid email or password\"}");
            }
        } catch (NonUniqueResultException ex) {
            // Handle the case where multiple clients with the same email exist
            return ResponseEntity.status(500).body("{\"error\": \"Internal Server Error\"}");
        }
    }

}