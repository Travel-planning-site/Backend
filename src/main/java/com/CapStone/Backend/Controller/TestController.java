package com.CapStone.Backend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TestController {

    @Value("${Kakao-API-Key}")
    private String Kakao_API_Key;

    @GetMapping("/test")
    public Boolean testCallback() {
        System.out.println("kakao key 값 : " + Kakao_API_Key);
        return true;
    }

}
