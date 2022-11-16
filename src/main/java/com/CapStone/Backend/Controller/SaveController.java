package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.BasicInfoRequest;
import com.CapStone.Backend.Dto.TravleInfoRequest;
import com.CapStone.Backend.Service.BasicInfoService;
import com.CapStone.Backend.Service.TravleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
@CrossOrigin
@RequiredArgsConstructor
public class SaveController {

    private final BasicInfoService basicInfoService;
    private final TravleInfoService travleInfoService;

    @PostMapping("/basic")
    public int saveInfo(@RequestBody BasicInfoRequest request) {
        System.out.println(request.toString());
        return basicInfoService.saveInfo(request);
    }

    @PostMapping("/travle")
    public void travlePlan(@RequestBody TravleInfoRequest request) {
        System.out.println(request.toString());
        travleInfoService.saveTravleInfo(request);
    }
}
