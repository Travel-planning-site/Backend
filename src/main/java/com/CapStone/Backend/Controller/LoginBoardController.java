package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.TokenResponseDto;
import com.CapStone.Backend.Dto.UserResponseDto;
import com.CapStone.Backend.Entity.User;
import com.CapStone.Backend.Service.LoginBoard.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin
public class LoginBoardController {

    private String ENDPOINT = "https://accounts.google.com/o/oauth2/v2/auth";

    @Value("${Google-Client-ID}")
    private String CLIENT_ID = "";
    @Value("${Google-RedirectToken-URL}")
    private String REDIRECT_URI = "";
    private String RESPONSE_TYPE = "code";
    private String SCOPE = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
    private final UserService userService;

    private String token;

    @GetMapping("/login")
    public String login() {
        System.out.println("동작");
        return "redirect:" + ENDPOINT + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=" + RESPONSE_TYPE + "&scope=" + SCOPE;
    }

    @GetMapping("/oauth/google/callback")
    public String oauthLogin(@RequestParam("code")String code) {
        System.out.println("콜백 동작");
        String token = userService.oauthLogin(code);
        this.token = token;
        return "redirect:" + "http://localhost:8080/#/Login";
    }

    @GetMapping("/token")
    public ResponseEntity<TokenResponseDto> getToken() {
        System.out.println("getToken() 동작");
        return new ResponseEntity<>(new TokenResponseDto(token, "bearer"), HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserResponseDto> getUserFromToken(HttpServletRequest request) {
        Long id = (Long) request.getAttribute("userId");
        User user = userService.findById((Long) request.getAttribute("userId"));
        return new ResponseEntity<>(new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserEmail()), HttpStatus.OK);
    }

}
