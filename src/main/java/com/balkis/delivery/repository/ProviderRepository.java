package com.balkis.delivery.repository;

import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Provider;
import org.springframework.data.repository.CrudRepository;
public interface ProviderRepository extends CrudRepository<Provider, Long>{
    Provider findByEmail(String email);
}
