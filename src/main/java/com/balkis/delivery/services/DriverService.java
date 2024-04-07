package com.balkis.delivery.services;
import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;

import com.balkis.delivery.models.User;
import com.balkis.delivery.repository.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class DriverService {

    private final DriverRepository driverrepository;

    public DriverService(DriverRepository driverrepository) {
        this.driverrepository = driverrepository;
    }

    public List<Driver> getAllDrivers() {
        return (List<Driver>) driverrepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverrepository.findById(id);
    }

    public Driver saveDriver(Driver driver) {
        return driverrepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverrepository.deleteById(id);
    }


    public void registerDriver(Driver driver) {

        saveDriver(driver);
    }

    public Driver findDriverByEmail(String email) {
        return driverrepository.findByEmail(email);
    }

    // Add the following method for login
}




