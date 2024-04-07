package com.balkis.delivery.repository;

import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;
import com.balkis.delivery.models.User;
import org.springframework.data.repository.CrudRepository;
public interface DriverRepository extends CrudRepository<Driver, Long>{
    Driver findByEmail(String email);
}
