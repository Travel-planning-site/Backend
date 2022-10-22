package com.CapStone.Backend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@CrossOrigin
public class LoginBoardController {

    @GetMapping("/auth")
    public void requestAccessToken(@RequestParam("code") String code) {
        System.out.println(code);
    }

}
