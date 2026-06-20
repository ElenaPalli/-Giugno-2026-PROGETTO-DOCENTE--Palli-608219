package it.uniroma3.siw.calcio.authentication;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.calcio.model.Credentials;
import it.uniroma3.siw.calcio.model.User;
import it.uniroma3.siw.calcio.service.CredentialsService;

@Service
public class CustomOAuth2UserService extends OidcUserService {

    private final CredentialsService credentialsService;

    public CustomOAuth2UserService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        String email = oidcUser.getAttribute("email");
        String name = oidcUser.getAttribute("name");
        String givenName = oidcUser.getAttribute("given_name");
        String familyName = oidcUser.getAttribute("family_name");

        if (email == null) {
            email = oidcUser.getName(); // Fallback
        }

        Credentials credentials = credentialsService.getCredentials(email);
        if (credentials == null) {
            User newUser = new User();
            newUser.setName(givenName != null ? givenName : (name != null ? name : "User"));
            newUser.setSurname(familyName != null ? familyName : "");
            newUser.setUsername(email);

            credentials = new Credentials();
            credentials.setUser(newUser);
            credentials.setUsername(email);
            credentials.setPassword(UUID.randomUUID().toString()); 
            credentialsService.saveCredentials(credentials);
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(credentials.getUsername())
                .password(credentials.getPassword())
                .authorities(credentials.getRole())
                .build();

        return new OAuth2UserDetails(oidcUser, userDetails);
    }
}
