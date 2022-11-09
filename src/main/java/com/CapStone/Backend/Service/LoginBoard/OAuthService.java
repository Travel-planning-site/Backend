package com.CapStone.Backend.Service.LoginBoard;

import com.CapStone.Backend.Dto.GoogleUserDto;
import com.CapStone.Backend.Dto.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${Google-Client-ID}")
    private String Google_Client_ID;

    @Value("${Google-Secret}")
    private String Google_Secret;

    @Value("${Google-RedirectToken-URL}")
    private String Google_RedirectToken_Url;

    public OAuthService(RestTemplate restTemplate) {
        this.objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createPostRequest(String code) {
        String url = "https://oauth2.googleapis.com/token";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", Google_Client_ID);
        params.add("client_secret", Google_Secret);
        params.add("redirect_uri", Google_RedirectToken_Url);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
    }

    public OAuthToken getAccessToken(ResponseEntity<String> response) {
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return oAuthToken;
    }

    public ResponseEntity<String> createGetRequest(OAuthToken oAuthToken) {
        String url = "https://www.googleapis.com/oauth2/v1/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + oAuthToken.getAccessToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);

        return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }

    public GoogleUserDto getUserInfo(ResponseEntity<String> userInfoResponse) {
        GoogleUserDto googleUserDto = null;
        System.out.println("야 : " + userInfoResponse);
        try {
            googleUserDto = objectMapper.readValue(userInfoResponse.getBody(), GoogleUserDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return googleUserDto;
    }

}
