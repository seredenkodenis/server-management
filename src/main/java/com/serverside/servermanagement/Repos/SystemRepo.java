package com.serverside.servermanagement.Repos;

import com.serverside.servermanagement.Entitiy.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepo extends JpaRepository<System,Integer> {
}
