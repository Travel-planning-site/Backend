package com.CapStone.Backend.Dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TravelInfoRequest {
    private int infoIdx;
    private String startPlaceImg;
    private String arrivalPlaceImg;
    private String startPlace;
    private String arrivalPlace;
    private String totalTime;
    private int cost;
    private String startTime;
    private String arriveTime;
    private String transportation;
    private String memo;
    private long userId;
}
