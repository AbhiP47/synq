package com.synq.config;

import com.synq.entity.User;
import com.synq.enums.Provider;
import com.synq.helpers.AppConstants;
import com.synq.repository.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.endpoint.DefaultOAuth2AccessTokenResponseMapConverter;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthenticationSuccessHandler.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        logger.info("OAuthSuccessHandler");

//        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationId);

//        logger.info(user.getName());
//        user.getAttributes().forEach((key,value)->{
//            logger.info("{} => {}",key,value);
//        });
//        logger.info(user.getAuthorities().toString());

        var oAuthUser = (DefaultOAuth2User)authentication.getPrincipal();

        oAuthUser.getAttributes().forEach((key,value) ->{
            logger.info(key+" : "+value);
        });

        String email = oAuthUser.getAttribute("email");
        Optional<User> userOptional = userRepo.findByEmail(email);
        User user;
        if(userOptional.isPresent())
        {
            logger.info("User already exists in the database.... Updating details and logging in.");
            user = userOptional.get();
        }
        else {
            logger.info("First time login for this user... Creating records in the database");
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setEmailVerified(true);
            newUser.setEnabled(true);
            newUser.setRoleList(List.of(AppConstants.ROLE_USER));
            newUser.setPassword(null);

            if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {
                newUser.setName(oAuthUser.getAttribute("name"));
                newUser.setProfilePic(oAuthUser.getAttribute("picture"));
                newUser.setProvider(Provider.GOOGLE);
            } else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {
                String firstName = oAuthUser.getAttribute("given_name");
                String lastName = oAuthUser.getAttribute("family_name");
                newUser.setName(firstName + " " + lastName);
                newUser.setProvider(Provider.LINKEDIN);
                newUser.setProfilePic(oAuthUser.getAttribute("picture"));
            }
            userRepo.save(newUser);
        }
        redirectStrategy.sendRedirect(request, response, "/user/profile");


    }
}
