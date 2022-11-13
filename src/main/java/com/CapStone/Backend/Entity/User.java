package com.CapStone.Backend.Entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {

    @Id
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String accessToken;

}
