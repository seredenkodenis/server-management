package com.serverside.servermanagement.Repos;

import com.serverside.servermanagement.Entitiy.Proc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepo extends CrudRepository<Proc, String> {
    Proc findProcByPid(Integer pid);
}
