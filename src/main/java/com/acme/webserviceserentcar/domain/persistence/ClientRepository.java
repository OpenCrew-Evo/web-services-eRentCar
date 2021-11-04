package com.acme.webserviceserentcar.domain.persistence;

import com.acme.webserviceserentcar.domain.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}