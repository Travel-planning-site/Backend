package com.CapStone.Backend.Service.ResearchPlace;

import com.CapStone.Backend.Dto.PlaceInfoDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Setter
public class ResearchPlaceService {

    private final KakaoSearchService kakaoSearchService;
    private final KakaoPhotoService kakaoPhotoService;
    private String keyword;

    public ArrayList<ArrayList<PlaceInfoDTO>> kakaoSearch() {
        // 카카오 로컬 API를 이용한 장소 검색
        int pageAmount = 3;
        ArrayList<ArrayList<PlaceInfoDTO>> placeList = new ArrayList<>();
        Gson gson = new Gson();

//        System.out.println("ResearchPlaceService_kakaoSearch keyword 값 : " + keyword);
        for (int i = 1; i <= pageAmount; i++) {
            placeList.add(kakaoSearchService.getKakaoSearch(keyword, i));
        }
//        System.out.println("[ResearchPlaceService]검색된 총 장소 갯수 : " + placeList.size());

        return placeList;
    }


    public ArrayList<ArrayList<PlaceInfoDTO>> daumSearch(ArrayList<ArrayList<PlaceInfoDTO>> placeLists) {
        // 다음 검색 API를 이용한 장소 사진
        ArrayList<PlaceInfoDTO> placeList;
        for (int i=0;i<placeLists.size();i++) {
            placeList = placeLists.get(i);
            placeLists.set(i, kakaoPhotoService.requestKakaoPhoto(placeList));
            // placeLists 수정
        }
        return placeLists;
    }

}
