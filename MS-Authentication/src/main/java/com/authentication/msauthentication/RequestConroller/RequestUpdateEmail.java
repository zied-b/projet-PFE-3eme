package com.authentication.msauthentication.RequestConroller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateEmail {
    private String CurrentEmail;
    private String NewEmail;
}
