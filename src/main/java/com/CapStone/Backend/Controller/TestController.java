package com.CapStone.Backend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TestController {

    @Value("${GOOGLE-MAP-KEY}")
    private String Google_Map_Api;

    @GetMapping("/test")
    public Boolean testCallback() {
        System.out.println("google key 값 : " + Google_Map_Api);
        return true;
    }

}
