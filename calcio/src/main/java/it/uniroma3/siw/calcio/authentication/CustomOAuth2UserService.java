package it.uniroma3.siw.calcio.authentication;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.calcio.model.Credentials;
import it.uniroma3.siw.calcio.model.User;
import it.uniroma3.siw.calcio.service.CredentialsService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final CredentialsService credentialsService;

    public CustomOAuth2UserService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String givenName = oauth2User.getAttribute("given_name");
        String familyName = oauth2User.getAttribute("family_name");

        if (email == null) {
            email = oauth2User.getName(); // Fallback
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

        return new OAuth2UserDetails(oauth2User, userDetails);
    }
}
