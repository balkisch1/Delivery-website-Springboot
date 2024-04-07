package com.balkis.delivery.repository;

import com.balkis.delivery.models.User;
import com.balkis.delivery.models.Userproduct;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserproductRepository extends JpaRepository<Userproduct, Long> {
    // Add custom queries if needed
}


