package com.authentication.msauthentication.Security.ConfigRsa;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
@ConfigurationProperties(prefix = "rsa")
//class recupere  ky
public record RsaKeyConfig(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}
