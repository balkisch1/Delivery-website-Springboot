package com.balkis.delivery.repository;

import com.balkis.delivery.models.Category;
import com.balkis.delivery.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}



