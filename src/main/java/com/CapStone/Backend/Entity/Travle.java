package com.CapStone.Backend.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Travle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private int travleIdx;

    @Column(nullable = false)
    private int infoIdx;

    @Column(nullable = false)
    private String startPlaceImg;

    @Column(nullable = false)
    private String arrivalPlaceImg;

    @Column(nullable = false)
    private String startPlace;

    @Column(nullable = false)
    private String arrivalPlace;

    @Column(nullable = false)
    private String totalTime;

    @Column(nullable = false)
    private int cost;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String arriveTime;

    @Column(nullable = false)
    private String transportation;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private int date;

    @Column(nullable = false)
    private Long userId;

}
