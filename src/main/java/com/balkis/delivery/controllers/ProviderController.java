package com.balkis.delivery.controllers;


import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;
import com.balkis.delivery.models.Provider;
import com.balkis.delivery.models.User;
import com.balkis.delivery.security.JwtUtils;
import com.balkis.delivery.services.ClientService;
import com.balkis.delivery.services.DriverService;
import com.balkis.delivery.services.ProviderService;

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
@RequestMapping("/api/provider")
public class ProviderController {

    private final ProviderService providerService;
    private final ClientService clientService;
    private final DriverService driverService;

    @Autowired
    private JwtUtils jwtUtils;
    public ProviderController(ProviderService providerService, ClientService clientService, DriverService driverService) {
        this.providerService = providerService;
        this.clientService = clientService;
        this.driverService = driverService;
    }

    @GetMapping
    public List<Provider> getAProvider() {
        return providerService.getAllProviders();
    }

    @GetMapping("/{id}")
    public Optional<Provider> getProviderById(@PathVariable Long id) {
        return providerService.getProviderById(id);
    }

    @PostMapping
    public Provider saveProvider(@RequestBody Provider provider) {
        return providerService.saveProvider(provider);
    }

    @DeleteMapping("/{id}")
    public void deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
    }
    @PostMapping("/register")
    public ResponseEntity<Object> registerProvider(@RequestBody Provider provider) {
        // Common user registration logic
        providerService.registerProvider(provider);

        String successMessage = "provider registered successfully";

        Map<String, Object> providerResponse = new HashMap<>();
        providerResponse.put("message", successMessage);
        providerResponse.put("provider", getProviderResponse(provider));

        return ResponseEntity.status(HttpStatus.CREATED).body(providerResponse);
    }
    private Map<String, Object> getProviderResponse(Provider provider) {
        Map<String, Object> providerMap = new HashMap<>();
        providerMap.put("firstName", provider.getFirstName());
        providerMap.put("lastName", provider.getLastName());
        providerMap.put("phone", provider.getPhone());
        providerMap.put("username", provider.getUsername());
        providerMap.put("email", provider.getEmail());
        providerMap.put("company", provider.getCompany());

        return providerMap;
    }
    @GetMapping("/client")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/driver")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }



    // Modify your login endpoint in UserController
    @PostMapping("/login")
    public ResponseEntity<?> loginProvider(@RequestBody Provider loginProvider) {
        try {
            // Retrieve the user by email
            Provider provider = providerService.findProviderByEmail(loginProvider.getEmail());

            if (provider != null && provider.checkPassword(loginProvider.getPassword())) {
                String token = jwtUtils.generateToken(provider.getEmail());

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
