package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Entity.Info;
import com.CapStone.Backend.Service.BasicInfoService;
import com.CapStone.Backend.Service.TravleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myPlan")
@CrossOrigin
@RequiredArgsConstructor
public class MyPageController {

    private final BasicInfoService basicInfoService;
    private final TravleInfoService travleInfoService;

    @GetMapping("/plan")
    public List<Info> saveInfo() {
        return basicInfoService.selectPlan();
    }
}
