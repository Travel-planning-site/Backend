package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.PlaceInfoDTO;
import com.CapStone.Backend.Service.ResearchPlace.ResearchPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ResearchPlace")
@CrossOrigin
public class ResearchPlaceController {

    private final ResearchPlaceService ResearchPlaceService;


    @GetMapping("/searchplace")
    public ResponseEntity<Boolean> searchPlace(@RequestParam("input") String input) {
        System.out.println("검색어 : " + input);
        ResearchPlaceService.setKeyword(input);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/getsearch")
    public ResponseEntity<ArrayList<ArrayList<PlaceInfoDTO>>> getSearchResult(){
        return new ResponseEntity<>(searchPhoto(), HttpStatus.OK);
    }

    // 본래 input은 없어야함. input으로 주소들이 들어오기 때문에
    public ArrayList<ArrayList<PlaceInfoDTO>> searchPhoto() {
        return ResearchPlaceService.daumSearch(getplaceList());
    }

    public ArrayList<ArrayList<PlaceInfoDTO>> getplaceList() {
        return ResearchPlaceService.kakaoSearch();
    }

}
