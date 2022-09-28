package com.CapStone.Backend.Dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceInfoDTO {
    private long placeId;
    private String placeName;
    private String placeAddr;
    private float placeX;
    private float placeY;
    private String placeImage;
    private int placeNumber;
}
