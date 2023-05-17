package com.authentication.msauthentication.Repo;


import com.authentication.msauthentication.Entity.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoRoles extends JpaRepository<roles,Integer> {
    Optional<roles> findByRole(String role);

}
