package com.serverside.servermanagement.Repos;

import com.serverside.servermanagement.Entitiy.LogServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogServiceRepo extends JpaRepository<LogServices, Integer> {
}
