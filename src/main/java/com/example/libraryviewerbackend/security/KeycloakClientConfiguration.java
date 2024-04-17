package com.example.libraryviewerbackend.security;

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
                .serverUrl("http://localhost:9009/")
                .realm("libraryViewer")
                .clientId("libraryViewer")
                .grantType(OAuth2Constants.PASSWORD)
                .username("testuser")
                .password("testpassword")
                .build();
    }
}
