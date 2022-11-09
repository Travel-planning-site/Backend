package com.CapStone.Backend.Service.LoginBoard;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class TokenMananger {

    @Value("${Google-Client-ID}")
    private String Google_Client_ID;

    @Value("${Google-Secret}")
    private String Google_Secret;

    @Value("${Google-RedirectToken-URL}")
    private String Google_RedirectToken_Url;

    private String accessToken;

    public void requestToGoogle(String code) {
        BufferedReader br = null;
        String access_Token = "";
        String refresh_token = "";
        String reqUrl = "https://www.googleapis.com/oauth2/v4/token";
        String line = "";
        String result = "";
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값인 false를 true로
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스프림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("code=" + code);
            sb.append("&client_id=" + Google_Client_ID);
            sb.append("&client_secret=" + Google_Secret);
            sb.append("&redirect_uri=" + Google_RedirectToken_Url);
            sb.append("&grant_type=authorization_code");
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode" + responseCode);

            // 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body" + result);

            // Gson 라이브러리에 포함된 클래스로 JSON 파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_token);

            br.close();
            bw.close();

            this.accessToken =access_Token;


        } catch (IOException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return this.accessToken;
    }

}
