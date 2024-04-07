package com.balkis.delivery.repository;

import com.balkis.delivery.models.Ordre;
import org.springframework.data.repository.CrudRepository;
public interface OrderRepository extends CrudRepository<Ordre, Long> {
}
