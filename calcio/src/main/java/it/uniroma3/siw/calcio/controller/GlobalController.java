package it.uniroma3.siw.calcio.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
    @ModelAttribute("userDetails")

    public UserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return (UserDetails) principal;
            } else if (principal instanceof org.springframework.security.oauth2.core.user.OAuth2User) {
                org.springframework.security.oauth2.core.user.OAuth2User oauth2User = 
                    (org.springframework.security.oauth2.core.user.OAuth2User) principal;
                String username = oauth2User.getAttribute("email");
                if (username == null) username = oauth2User.getName();
                return org.springframework.security.core.userdetails.User.builder()
                        .username(username)
                        .password("")
                        .authorities("DEFAULT")
                        .build();
            }
        }
        return null;
    }

    @ModelAttribute("loggedUsername")
    public String getLoggedUsername() {
        UserDetails user = getUser();
        return user != null ? user.getUsername() : null;
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals(it.uniroma3.siw.calcio.model.Credentials.ADMIN_ROLE));
        }
        return false;
    }
}