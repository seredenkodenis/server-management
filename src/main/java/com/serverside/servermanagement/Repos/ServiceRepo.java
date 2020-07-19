package com.serverside.servermanagement.Repos;

import com.serverside.servermanagement.Entitiy.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service, String> {
    Service findByName(String name);
}
