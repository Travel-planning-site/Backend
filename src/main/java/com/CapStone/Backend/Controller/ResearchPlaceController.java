package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Service.GooglePlace;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @GetMapping("/search")
    public void searchPlace(@RequestParam("input") String input) {
        System.out.println("검색어 : " + input);
        GooglePlace.FindPlace(input);
    }

}
