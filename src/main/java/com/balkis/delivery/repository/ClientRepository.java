package com.balkis.delivery.repository;

import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;
import org.springframework.data.repository.CrudRepository;
public interface ClientRepository extends CrudRepository<Client, Long>{
    Client findByEmail(String email);

}
