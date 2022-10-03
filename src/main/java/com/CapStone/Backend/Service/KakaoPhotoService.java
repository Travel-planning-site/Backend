package com.CapStone.Backend.Service.ResearchPlace;

import com.CapStone.Backend.Dto.PlaceInfoDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class KakaoPhotoService {

    @Value("${Kakao-API-Key}")
    private String Kakao_API_Key;

    public ArrayList<PlaceInfoDTO> requestKakaoPhoto(ArrayList<PlaceInfoDTO> placeList) {
        String imageUrl;
        for (int i=0;i<placeList.size();i++) {
            imageUrl = getKakaoPhoto(placeList.get(i).getPlaceAddr());
            placeList.get(i).setPlaceImage(imageUrl);
        }
        return placeList;
    }

    public String getKakaoPhoto(String query) {
        BufferedReader br = null;
        String imageUrl = null;
        try {
            String encode = URLEncoder.encode(query, "UTF-8");
            String reqUrl = "https://dapi.kakao.com/v2/search/image?sort=accuracy&page=1&size=1&query="+encode;
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Requested-With", "curl");
            conn.setRequestProperty("Authorization", "KakaoAK " + Kakao_API_Key);

            conn.setDoOutput(true);

            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
            JsonArray jsonArr = jsonObject.getAsJsonArray("documents");
            if (jsonArr.size()!=0) {
                imageUrl = jsonArr.get(0).getAsJsonObject().get("image_url").getAsString();
            }
            else {
                imageUrl = "https://cdn.smartlifetv.co.kr/news/photo/202107/12282_15191_4027.jpg";
            }
        } catch (IOException e) {
            System.out.println("getKakaoPhoto 오류 발생");
            e.printStackTrace();
        }
        return imageUrl;
    }

}
