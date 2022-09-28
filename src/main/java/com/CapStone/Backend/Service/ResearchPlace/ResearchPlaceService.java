package com.CapStone.Backend.Service.ResearchPlace;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResearchPlaceService {

    private final KakaoSearchService KakaoSearchService;
    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void kakaoSearch() {
        int pageAmount = 3;
        System.out.println("ResearchPlaceService_kakaoSearch keyword 값 : " + keyword);
        for (int i = 1; i <= pageAmount; i++) {
            KakaoSearchService.getKakaoSearchObject(keyword, i);
        }
    }

}
