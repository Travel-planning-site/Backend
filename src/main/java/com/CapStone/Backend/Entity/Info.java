package com.CapStone.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private int idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String people;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String memo;

    public int getIdx() {
        return idx;
    }
}
