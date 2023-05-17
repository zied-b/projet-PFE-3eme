package com.computerhardware.mscomputerhardware.Repo;


import com.computerhardware.mscomputerhardware.Entity.computerHardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepoMI extends JpaRepository<computerHardware,Integer> {

    @Query("select cm.idMi from computerHardware cm where cm.ref=:ref ")
    Optional<Integer> getIdByRef(@Param("ref") String ref);
    Integer countByRef(String ref);



}
