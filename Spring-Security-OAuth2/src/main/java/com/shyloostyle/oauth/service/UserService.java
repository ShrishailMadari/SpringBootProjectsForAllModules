package com.shyloostyle.oauth.service;

import com.shyloostyle.oauth.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User createUser(OAuth2User oAuth2User) {
        User user = new User();
        // Set user attributes from OAuth2User
        user.setName(oAuth2User.getAttribute("name"));
        user.setEmail(oAuth2User.getAttribute("email"));
        return user;
    }
}
