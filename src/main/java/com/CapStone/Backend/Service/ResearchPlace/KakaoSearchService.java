package com.CapStone.Backend.Service.ResearchPlace;

import com.CapStone.Backend.Dto.PlaceInfoDTO;
import com.google.gson.JsonArray;
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
import java.net.URLEncoder;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class KakaoSearchService {

    @Value("${Kakao-API-Key}")
    private String Kakao_API_Key;

    public ArrayList<PlaceInfoDTO> getKakaoSearch(String keyword, int pageNum) {
        BufferedReader br = null;
        ArrayList<PlaceInfoDTO> placeList = new ArrayList<>();

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
//            System.out.println("결과 값 : " + result);

            placeList = getPlaceList(result);

//            System.out.println("placeList : " + placeList.size());


        } catch (IOException e) {
            System.out.println("오류 발생");
            e.printStackTrace();
        }
        return placeList;
    }

    public ArrayList<PlaceInfoDTO> getPlaceList(String result) {

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray jsonArr = jsonObject.getAsJsonArray("documents");
        ArrayList<PlaceInfoDTO> placeList = new ArrayList<>();
        for (int i=0; i < jsonArr.size(); i++) {
            placeList.add(buildPlaceList((JsonObject) jsonArr.get(i), i));
        }
//        System.out.println("[KakaoSearchService]검색된 총 장소 갯수 : " + placeList.size());
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
        return placeDTO;
    }

}
