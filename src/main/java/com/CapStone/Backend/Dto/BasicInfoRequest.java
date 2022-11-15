package com.CapStone.Backend.Dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BasicInfoRequest {
    private String title;
    private String place;
    private String people;
    private int period;
    private Long userId;
    private String memo;
}
