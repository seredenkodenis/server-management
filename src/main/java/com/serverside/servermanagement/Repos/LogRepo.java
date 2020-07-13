package com.serverside.servermanagement.Repos;

import com.serverside.servermanagement.Entitiy.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepo extends JpaRepository<Log, Integer> {
}
