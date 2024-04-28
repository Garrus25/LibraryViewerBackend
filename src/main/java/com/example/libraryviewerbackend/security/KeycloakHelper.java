package com.example.libraryviewerbackend.security;

import com.example.libraryviewerbackend.modelmapper.UserModelMapper;
import com.example.libraryviewerbackend.utils.KeycloakCredentialsProvider;
import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class KeycloakHelper {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakHelper.class);

    private static final String REALM_NAME = "LibraryViewer";

    private final Keycloak keycloak;

    public KeycloakHelper(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public SecurityEntity checkUserCredentials(UserCredentialsDTO userCredentialsDTO) {
        try (Keycloak tempSession = keycloakBuilderWithCredentials(userCredentialsDTO.getUsername(),
                userCredentialsDTO.getPassword())) {
            tempSession.tokenManager().getAccessToken();
            SecurityEntity securityEntity = new SecurityEntity();
            securityEntity.setToken(tempSession.tokenManager().getAccessTokenString());
            securityEntity.setRefreshToken(tempSession.tokenManager().refreshToken().getRefreshToken());
            return securityEntity;
        } catch (Exception e){
            return null;
        }
    }

    public List<UserRepresentation> getAllUsers(){
        try {
            return keycloak.realm(REALM_NAME)
                    .users().list();
        } catch (NotFoundException e) {
            logger.info("Could not find any users");
            return new LinkedList<>();
        }
    }

    public Response createUser(UserDTO userDTO){
        RealmResource realmResource = keycloak.realm(REALM_NAME);
        UsersResource usersResource = realmResource.users();

        UserRepresentation userRepresentation = UserModelMapper.toEntity(userDTO);
        userRepresentation.setEnabled(true);
        userRepresentation.setRealmRoles(List.of("user"));
        userRepresentation.setEmailVerified(true);

        return usersResource.create(userRepresentation);
    }

    private Keycloak keycloakBuilderWithCredentials(String login, String password) {
        return KeycloakBuilder.builder()
                .serverUrl(KeycloakCredentialsProvider.getUrl())
                .realm(KeycloakCredentialsProvider.getRealm())
                .clientId(KeycloakCredentialsProvider.getClientId())
                .grantType(OAuth2Constants.PASSWORD)
                .username(login)
                .password(password)
                .build();
    }

}
