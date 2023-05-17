package com.moyennetransport.msmoyennetransport.Security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record ConfigRsa(RSAPublicKey publicKey) {
}
