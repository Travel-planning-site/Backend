package com.CapStone.Backend.Service.ResearchPlace;

import com.CapStone.Backend.Dto.PlaceInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class KakaoSearchService {

    @Value("${Kakao-API-Key}")
    private String Kakao_API_Key;

    public void getKakaoSearchObject(String keyword, int pageNum) {
        System.out.println(pageNum + "번째 KakaoSearchService_getKakaoSearchObject keyword 값 : " + keyword);
        BufferedReader br = null;
        JsonObject obj = new JsonObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String encode = URLEncoder.encode(keyword, "UTF-8");
            String reqURL = "https://dapi.kakao.com/v2/local/search/keyword.json?query="+encode+"&page="+pageNum;
            URL url = new URL(reqURL);
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
            System.out.println("결과 값 : " + result);

            getPlaceList(result);


        } catch (IOException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        }
    }

    public ArrayList<PlaceInfoDTO> getPlaceList(String result) {

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray jsonArr = jsonObject.getAsJsonArray("documents");
        ArrayList<PlaceInfoDTO> placeList = new ArrayList<>();
        for (int i=0; i < jsonArr.size(); i++) {
            placeList.add(buildPlaceList((JsonObject) jsonArr.get(i), i));
        }
        return placeList;
    }

    public PlaceInfoDTO buildPlaceList(JsonObject obj, int i) {
        PlaceInfoDTO placeDTO = PlaceInfoDTO.builder()
                .placeId(obj.get("id").getAsLong())
                .placeName(obj.get("place_name").getAsString())
                .placeAddr(obj.get("address_name").getAsString())
                .placeX(obj.get("x").getAsFloat())
                .placeY(obj.get("y").getAsFloat())
                .placeImage(null)
                .placeNumber(i)
                .build();

        System.out.println(i+"번째 장소 이름 : " + placeDTO.getPlaceName());
        return placeDTO;
    }

}
