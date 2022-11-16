package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.BasicInfoRequest;
import com.CapStone.Backend.Dto.TravelInfoRequest;
import com.CapStone.Backend.Service.BasicInfoService;
import com.CapStone.Backend.Service.TravelInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
@CrossOrigin
@RequiredArgsConstructor
public class SaveController {

    private final BasicInfoService basicInfoService;
    private final TravelInfoService travelInfoService;

    @PostMapping("/basic")
    public int saveInfo(@RequestBody BasicInfoRequest request) {
        System.out.println(request.toString());
        return basicInfoService.saveInfo(request);
    }

    @PostMapping("/travle")
    public void travlePlan(@RequestBody TravelInfoRequest request) {
        System.out.println(request.toString());
        travelInfoService.saveTravleInfo(request);
    }
}
