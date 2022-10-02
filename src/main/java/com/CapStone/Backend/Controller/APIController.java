package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Service.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class APIController {
    private final APIService apiService;
    // 네이버 검색 Image API
    @GetMapping("/searchImg")
    public String searchImgApi(String query) {
        String imgUrl = apiService.searchImg(query);
        return imgUrl;
    }

    // 카카오 Navi API

    // 구글 맵 API
}
