package com.CapStone.Backend.Dto;

import com.CapStone.Backend.Entity.Info;
import com.CapStone.Backend.Entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class BasicInfoResponse {
    Info info;
    List<Travel> travels;
}
