package com.serverside.servermanagement.Repos;

import com.serverside.servermanagement.Entitiy.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskRepo extends JpaRepository<Disk, Integer> {
}
