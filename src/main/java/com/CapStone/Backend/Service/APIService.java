package com.CapStone.Backend.Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIService {
    @Value("${X-Naver-Client-Secret}")
    private String NAVER_KEY;
    public String searchImg(String query) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://openapi.naver.com/v1/search/image?query="+query;

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", "o9H6ct1KNCA_QMfWXbtT");
        httpHeaders.set("X-Naver-Client-Secret", NAVER_KEY);

        HttpEntity entity = new HttpEntity(httpHeaders);

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        // Response JSON 파싱
        JSONParser parser = new JSONParser();

        JSONObject obj = null;

        try {
            obj = (JSONObject)parser.parse(response.getBody());
        } catch (ParseException e) {
            System.out.printf("변환 실패");
            e.printStackTrace();
        }

        JSONArray items = (JSONArray) obj.get("items");
        JSONObject firstItem = (JSONObject) items.get(0);
        String imgUrl = (String) firstItem.get("thumbnail");


        return imgUrl;
    }

}
