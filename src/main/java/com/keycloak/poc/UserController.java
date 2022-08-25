package com.keycloak.poc;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Map;

@Controller
public class UserController {

    @PostMapping(path = "/authentication")
    public ResponseEntity<String> checkAuthentication() {

        try {
            KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                    .getAuthentication();

            final Principal principal = (Principal) authentication.getPrincipal();

            if (principal instanceof KeycloakPrincipal) {

                KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
                IDToken token = kPrincipal.getKeycloakSecurityContext()
                        .getIdToken();

                Map<String, Object> customClaims = token.getOtherClaims();

                if (customClaims.containsKey("DOB")) {
                    String dob = String.valueOf(customClaims.get("DOB"));
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Authenticated!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Authenticated!");

        }
    }
}
