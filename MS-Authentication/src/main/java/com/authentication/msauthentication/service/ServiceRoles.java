package com.authentication.msauthentication.service;


import com.authentication.msauthentication.Entity.roles;
import com.authentication.msauthentication.Repo.RepoRoles;
import com.authentication.msauthentication.service.Interface.InterfaceServiceRoles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceRoles implements InterfaceServiceRoles {
    private final RepoRoles repoRoles ;

    public ServiceRoles(RepoRoles repoRoles) {
        this.repoRoles = repoRoles;
    }

    @Override
    public List<roles> fetchAllRoles() {
        return repoRoles.findAll();
    }

    @Override
    public void AddRole(roles role) {
        repoRoles.save(role);
    }

    @Override
    public Optional<roles> fetchRoleById(Integer id) {
        return repoRoles.findById(id);
    }
}
