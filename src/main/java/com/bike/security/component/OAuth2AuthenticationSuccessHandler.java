package com.bike.security.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.bike.security.service.UserService;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    RestTemplate restTemplate ;
    private final UserService userService;

    public OAuth2AuthenticationSuccessHandler(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate=restTemplate;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2AuthenticationToken oauthToken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = oauthToken.getPrincipal();
        System.out.println("Oauth2User " + oauthUser);
         String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

       String picture = oauthUser.getAttribute("picture");
        ResponseEntity<byte[]> imageResponse =
                restTemplate.getForEntity(picture, byte[].class);
        byte[] imageBytes = imageResponse.getBody();
          String googleId = oauthUser.getAttribute("sub");


        userService.createOrUpdateUser(
                email,
                name,
                picture,
                googleId,
                imageBytes

        );

        getRedirectStrategy().sendRedirect(
                request,
                response,
                "http://localhost:5173/home"
        );
    }

}
