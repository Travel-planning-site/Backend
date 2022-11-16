package com.CapStone.Backend.Dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CoordinateRequest {
    private double originX;
    private double originY;
    private double destinationX;
    private double destinationY;
}
