package com.CapStone.Backend.Controller;

import com.CapStone.Backend.Dto.BasicInfoResponse;
import com.CapStone.Backend.Service.BasicInfoService;
import com.CapStone.Backend.Service.TravleInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myPage")
@CrossOrigin
@RequiredArgsConstructor
public class MyPageController {

    private final BasicInfoService basicInfoService;
    private final TravleInfoService travleInfoService;

    @GetMapping("/plan")
    public List<BasicInfoResponse> saveInfo() { // 기본정보와 여행계획 같이 담아서 반환
        return basicInfoService.selectPlan();
    }

    @DeleteMapping("/plan/{idx}")
    public String deleteInfo(@PathVariable("idx") int idx) { // 기본정보와 여행계획 같이 담아서 반환
        return basicInfoService.deletePlan(idx);
    }
}
