package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.BasicInfoRequest;
import com.CapStone.Backend.Service.BasicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
@CrossOrigin
@RequiredArgsConstructor
public class SaveController {

    private final BasicInfoService basicInfoService;

    @PostMapping("/basic")
    public void saveInfo(@RequestBody BasicInfoRequest request) {
        System.out.println(request.toString());
        basicInfoService.saveInfo(request);
    }
}
