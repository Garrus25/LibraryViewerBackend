package com.example.libraryviewerbackend.security;

import com.example.libraryviewerbackend.utils.KeycloakCredentialsProvider;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfiguration {
    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(KeycloakCredentialsProvider.getUrl())
                .realm(KeycloakCredentialsProvider.getRealm())
                .clientId(KeycloakCredentialsProvider.getClientId())
                .grantType(OAuth2Constants.PASSWORD)
                .username(KeycloakCredentialsProvider.getLogin())
                .password(KeycloakCredentialsProvider.getPassword())
                .build();
    }
}
