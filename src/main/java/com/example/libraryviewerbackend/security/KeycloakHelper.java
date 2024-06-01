package com.example.libraryviewerbackend.security;

import com.example.libraryviewerbackend.modelmapper.KeycloakUserModelMapper;
import com.example.libraryviewerbackend.utils.KeycloakCredentialsProvider;
import com.openapi.gen.springboot.dto.SecurityEntity;
import com.openapi.gen.springboot.dto.UserCredentialsDTO;
import com.openapi.gen.springboot.dto.UserDTO;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class KeycloakHelper implements IKeycloakHelper {
    private static final Logger logger = LoggerFactory.getLogger(KeycloakHelper.class);

    private static final String REALM_NAME = "LibraryViewer";

    private final Keycloak keycloak;

    RealmResource realmResource;
    UsersResource usersResource;

    public KeycloakHelper(Keycloak keycloak) {
        this.keycloak = keycloak;
        realmResource = keycloak.realm(REALM_NAME);
        usersResource = realmResource.users();
    }

    @Override
    public SecurityEntity checkUserCredentials(UserCredentialsDTO userCredentialsDTO) {
        try (Keycloak tempSession = keycloakBuilderWithCredentials(userCredentialsDTO.getUsername(),
                userCredentialsDTO.getPassword())) {
            tempSession.tokenManager().getAccessToken();
            SecurityEntity securityEntity = new SecurityEntity();
            securityEntity.setToken(tempSession.tokenManager().getAccessTokenString());
            securityEntity.setRefreshToken(tempSession.tokenManager().refreshToken().getRefreshToken());

            UserRepresentation user = tempSession.realm(REALM_NAME).users().search(userCredentialsDTO.getUsername()).get(0);
            securityEntity.setId(user.getId());

            return securityEntity;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserRepresentation> getAllUsers(){
        try {
            return usersResource.list();
        } catch (NotFoundException e) {
            logger.info("Could not find any users");
            return new LinkedList<>();
        }
    }

    @Override
    public Response createUser(UserDTO userDTO){
        UserRepresentation userRepresentation = KeycloakUserModelMapper.toEntity(userDTO);
        userRepresentation.setEnabled(true);
        userRepresentation.setRealmRoles(List.of("user"));

        if (userRepresentation.getRequiredActions() == null) {
            userRepresentation.setRequiredActions(new LinkedList<>());
        }

        Response response = usersResource.create(userRepresentation);

        if (response.getStatus() == HttpStatus.CREATED.value()) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            sendConfirmationEmail(userId);
            userDTO.setId(userId);
        }

        return response;
    }

    @Override
    public Boolean checkIfEmailIsConfirmed(String userId){
        try {
            return usersResource.get(userId).toRepresentation().isEmailVerified();
        } catch (NotFoundException e){
            logger.warn("User with id {} not found", userId);
            return null;
        }
    }

    @Override
    public boolean sendConfirmationEmail(String userId){
        try {
            log.info("Sending email confirmation to user with id {}", userId);
            usersResource.get(userId).executeActionsEmail(null, null, null, List.of("VERIFY_EMAIL"));
            log.info("Confirmation email has been sent");
            return true;
        } catch (NotFoundException e){
            logger.warn("User with id {} not found", userId);
            return false;
        }
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
