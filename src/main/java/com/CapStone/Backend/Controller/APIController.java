package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.CoordinateRequest;
import com.CapStone.Backend.Dto.NavigationResponse;
import com.CapStone.Backend.Service.APIService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/KakaoNavi")
    public NavigationResponse kakaoNaviApi(@RequestBody CoordinateRequest coordinateRequest) throws ParseException {
        return apiService.getRoutes(coordinateRequest);
    }

    // 구글 맵 API
}
