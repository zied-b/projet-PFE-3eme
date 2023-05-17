package com.authentication.msauthentication.service.Interface;



import com.authentication.msauthentication.Entity.roles;

import java.util.List;
import java.util.Optional;

public interface InterfaceServiceRoles {
    List<roles> fetchAllRoles();
    void AddRole(roles role);
    Optional<roles> fetchRoleById(Integer id) ;
}
