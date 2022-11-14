package com.CapStone.Backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.simple.JSONArray;

@Data
@AllArgsConstructor
public class NavigationResponse {
    private long resultCode;
    private String resultMsg;
    private JSONArray guides;
    private long duration;
}
