package com.teststation.assessment.repository;

import com.teststation.assessment.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
