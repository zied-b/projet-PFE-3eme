package com.authentication.msauthentication.RequestConroller;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserPassword {
    @Email(message = "The email address entered is not valid. Please enter a valid email address (username@domain.com")
    String email;
    @Size(max = 20 ,min = 8 ,message = "Password must be between 8 and 20 characters")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9!@#$%^&*()_+={}|;':\",./<>?`~`-]*$", message="Password must contain 1 uppercase letter, 1 lowercase letter, and 1 number, and no spaces.")
    String password ;
}
