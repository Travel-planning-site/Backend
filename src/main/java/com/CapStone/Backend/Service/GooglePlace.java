package com.CapStone.Backend.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GooglePlace {

    public void FindPlace(String input) {
        String reqURL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
    }
}
