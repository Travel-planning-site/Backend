package com.CapStone.Backend.Dto;

import com.CapStone.Backend.Entity.User;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserDto {
    public Long id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;


    public User toUser(String accessToken) {
        System.out.println("id값 : " + id);
        return new User(id, email, name, accessToken);
    }

}
