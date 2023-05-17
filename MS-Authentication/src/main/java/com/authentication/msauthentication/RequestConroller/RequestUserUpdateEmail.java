package com.authentication.msauthentication.RequestConroller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RequestUserUpdateEmail {
   private Integer idClient;
   private String Email;
}
