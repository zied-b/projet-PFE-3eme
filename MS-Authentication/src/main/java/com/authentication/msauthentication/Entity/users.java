package com.authentication.msauthentication.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class users  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Integer idUser;

    String name ;

    private  String lastName=null;

    private String firstName=null;

    private Integer tlf=null;
    private  Boolean firstVisit=true;

    @Basic(optional = false)
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Basic(optional = false)
    String password ;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<roles> rolesUser=new ArrayList<>();
    @Basic(fetch = FetchType.EAGER)
    @Lob
    private byte[] image;

}
