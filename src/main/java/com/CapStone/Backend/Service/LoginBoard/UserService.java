package com.CapStone.Backend.Service.LoginBoard;

import com.CapStone.Backend.Dto.GoogleUserDto;
import com.CapStone.Backend.Dto.OAuthToken;
import com.CapStone.Backend.Entity.User;
import com.CapStone.Backend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final OAuthService oauthService;
    private final JwtManager jwtManager;

    public String oauthLogin(String code) {
        ResponseEntity<String> accessTokenResponse = oauthService.createPostRequest(code);
        OAuthToken oAuthToken = oauthService.getAccessToken(accessTokenResponse);
        logger.info("Access Token: {}", oAuthToken.getAccessToken());

        ResponseEntity<String> userInfoResponse = oauthService.createGetRequest(oAuthToken);
        GoogleUserDto googleUser = oauthService.getUserInfo(userInfoResponse);
        logger.info("Google User Name: {}", googleUser.getName());

        if (!isJoinedUser(googleUser)) {
            signUp(googleUser, oAuthToken);
        }
        User user = userRepository.findByEmail(googleUser.getEmail()).orElseThrow(IllegalArgumentException::new);
        return jwtManager.createToken(user.getId());
    }

    private boolean isJoinedUser(GoogleUserDto googleUser) {
        Optional<User> users = userRepository.findByEmail(googleUser.getEmail());
        logger.info("Joined User: {}", users);
        return users.isPresent();
    }

    private void signUp(GoogleUserDto googleUser, OAuthToken oAuthToken) {
        User user = googleUser.toUser(oAuthToken.getAccessToken());
        userRepository.save(user);
    }
}
