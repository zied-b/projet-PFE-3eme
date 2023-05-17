package com.authentication.msauthentication.RequestConroller;

import lombok.Data;


@Data
public class RequestNewPassword {
    String currentPassword;
    String newPassword ;
}
