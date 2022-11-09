package com.CapStone.Backend.Dto;

import com.CapStone.Backend.Entity.User;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserDto {
    public String id;
    public String email;
    public Boolean verifiedEmail;
    public String name;
    public String givenName;
    public String familyName;
    public String picture;
    public String locale;


    public User toUser(String accessToken) {
        return new User(Long.valueOf(id), email, name, accessToken);
    }

}
