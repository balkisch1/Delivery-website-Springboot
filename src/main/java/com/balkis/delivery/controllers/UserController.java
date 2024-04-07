package com.balkis.delivery.controllers;


import com.balkis.delivery.models.*;
import com.balkis.delivery.security.JwtUtils;
import com.balkis.delivery.services.ProviderService;
import com.balkis.delivery.services.UserService;
import com.balkis.delivery.services.ClientService;
import com.balkis.delivery.services.DriverService;
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
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;
    private final ClientService clientService;
    private final DriverService driverService;
    private final ProviderService providerService;

    @Autowired

    private JwtUtils jwtUtils;
    public UserController(UserService userService, ClientService clientService, DriverService driverService, ProviderService providerService) {
        this.userService = userService;
        this.clientService = clientService;
        this.driverService = driverService;
        this.providerService = providerService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, Object> payload) {
        if (!payload.containsKey("role") || !payload.containsKey("user")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role and user must be provided");
        }

        try {
            String role = (String) payload.get("role");
            Map<String, Object> userData = (Map<String, Object>) payload.get("user");

            switch (role.toLowerCase()) {
                case "client":
                    Client client = mapToClient(userData);
                    clientService.registerClient(client);
                    break;
                case "driver":
                    Driver driver = mapToDriver(userData);
                    driverService.registerDriver(driver);
                    break;
                case "provider":
                    Provider provider = mapToProvider(userData);
                    providerService.registerProvider(provider);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role");
            }

            String successMessage = "User registered successfully";
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            // Handle other exceptions, e.g., related to database
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }

    private Client mapToClient(Map<String, Object> userData) {
        return new Client(
                (String) userData.get("lastName"),
                (String) userData.get("firstName"),
                (String) userData.get("phone"),
                (String) userData.get("email"),
                (String) userData.get("username"),
                (String) userData.get("password"),
                (String) userData.get("localisation")
        );
    }
    private Provider mapToProvider(Map<String, Object> userData) {
        return new Provider(
                (String) userData.get("lastName"),
                (String) userData.get("firstName"),
                (String) userData.get("phone"),
                (String) userData.get("email"),
                (String) userData.get("username"),
                (String) userData.get("password"),
                (String) userData.get("company")
        );
    }

    private Driver mapToDriver(Map<String, Object> userData) {
        return new Driver(
                (String) userData.get("lastName"),
                (String) userData.get("firstName"),
                (String) userData.get("phone"),
                (String) userData.get("email"),
                (String) userData.get("username"),
                (String) userData.get("password"),
                (String) userData.get("adresse")
        );
    }









    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        try {
            // Retrieve the user by email
            User user = userService.findUserByEmail(loginUser.getEmail());

            if (user != null && user.checkPassword(loginUser.getPassword())) {
                String token = jwtUtils.generateToken(user.getEmail());

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
    }}