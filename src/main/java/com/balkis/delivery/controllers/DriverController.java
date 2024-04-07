package com.balkis.delivery.controllers;


import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;

import com.balkis.delivery.models.User;
import com.balkis.delivery.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Optional<Driver> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @PostMapping
    public Driver saveDriver(@RequestBody Driver client) {
        return driverService.saveDriver(client);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> registerDriver(@RequestBody Driver driver) {
        // Common user registration logic
        driverService.registerDriver(driver);

        String successMessage = "Driver registered successfully";

        Map<String, Object> driverResponse = new HashMap<>();
        driverResponse.put("message", successMessage);
        driverResponse.put("driver", getDriverResponse(driver));

        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponse);
    }

    // Helper method to create a driver-specific response
    private Map<String, Object> getDriverResponse(Driver driver) {
        Map<String, Object> driverMap = new HashMap<>();
        driverMap.put("firstName", driver.getFirstName());
        driverMap.put("lastName", driver.getLastName());
        driverMap.put("phone", driver.getPhone());
        driverMap.put("username", driver.getUsername());
        driverMap.put("email", driver.getEmail());
        driverMap.put("adresse", driver.getAdresse());

        return driverMap;
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginDriver(@RequestBody Client loginDriver) {
        // Retrieve the user by email
        Driver driver = driverService.findDriverByEmail(loginDriver.getEmail());

        if (driver != null && driver.checkPassword(loginDriver.getPassword())) {
            // Authentication successful
            // You might want to generate a token here and return it in the response
            return ResponseEntity.ok().body("{\"message\": \"Login successful\"}");
        } else {
            // Authentication failed
            return ResponseEntity.status(401).body("{\"error\": \"Invalid email or password\"}");
        }
    }
}