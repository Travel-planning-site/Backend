package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Service.ResearchPlace.ResearchPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ResearchPlace")
@CrossOrigin
public class ResearchPlaceController {

    private final ResearchPlaceService ResearchPlaceService;

    @GetMapping("/search")
    public void searchPlace(@RequestParam("input") String input) {
        System.out.println("검색어 : " + input);
        ResearchPlaceService.setKeyword(input);
        ResearchPlaceService.kakaoSearch();
    }

}
