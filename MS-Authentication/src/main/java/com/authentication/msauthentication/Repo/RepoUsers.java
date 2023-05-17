package com.authentication.msauthentication.Repo;


import com.authentication.msauthentication.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoUsers extends JpaRepository<users,Integer> {

    @Query("select r.idUser from users r where r.email=:email")
    Optional<Integer> getIdByEmail(@Param("email") String email);
   Optional<users> findByEmail(String Email);
    users findByName(String name);
    @Query("SELECT u FROM users u JOIN u.rolesUser r WHERE " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', ?1, '%')) OR " +
            "LOWER(r.role) LIKE LOWER(CONCAT('%', ?1, '%'))")
    Optional<List<users>> recherche(String recherche);

}
