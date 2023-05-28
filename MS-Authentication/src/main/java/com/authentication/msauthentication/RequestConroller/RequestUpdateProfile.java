package com.authentication.msauthentication.RequestConroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class RequestUpdateProfile {
    private  String lastName=null;

    private String firstName=null;

    private Integer tlf=null;
}
