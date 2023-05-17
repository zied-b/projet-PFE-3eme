package com.authentication.msauthentication.Security.Config;


import com.authentication.msauthentication.Entity.users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Data
public class ConfigUserDetails implements UserDetails {

    private String userName;
    private String password;
    private  Collection<GrantedAuthority> authorities =new ArrayList<>();

    public ConfigUserDetails(users user) {
        userName = user.getEmail();
        password = user.getPassword();
        user.getRolesUser().forEach(roles -> {
            authorities.add(new SimpleGrantedAuthority(roles.getRole()));
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return  authorities ;
    }



    @Override
    public String getUsername() {

        return userName;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
