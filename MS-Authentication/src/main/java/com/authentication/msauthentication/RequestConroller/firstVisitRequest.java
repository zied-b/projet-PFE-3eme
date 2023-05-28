package com.authentication.msauthentication.RequestConroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class firstVisitRequest {
    private String lastName,firstName,newPassword;
    private Integer tlf;
}
