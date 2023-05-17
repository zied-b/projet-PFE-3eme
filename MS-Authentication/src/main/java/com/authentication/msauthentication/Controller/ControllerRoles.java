package com.authentication.msauthentication.Controller;


import com.authentication.msauthentication.Entity.roles;
import com.authentication.msauthentication.service.Interface.InterfaceServiceRoles;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@PreAuthorize("hasAuthority('SCOPE_AdminSys')")

public class ControllerRoles {

    private InterfaceServiceRoles interfaceServiceRoles;

    public ControllerRoles(InterfaceServiceRoles interfaceServiceRoles) {
        this.interfaceServiceRoles = interfaceServiceRoles;
    }

    @GetMapping("/roles")
    public List<roles> fetchAllroles(){
        return interfaceServiceRoles.fetchAllRoles();
    }
    @PostMapping("/roles")
    public void AddNewRole(@RequestBody roles role){
        interfaceServiceRoles.AddRole(role);
    }
    @GetMapping("/roles/{id}")
    public Optional<roles> fetchRolesById(@PathVariable Integer id){
        return interfaceServiceRoles.fetchRoleById(id);
    }

}
