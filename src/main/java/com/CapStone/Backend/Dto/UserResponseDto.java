package com.CapStone.Backend.Dto;

import com.CapStone.Backend.Entity.User;
import com.CapStone.Backend.Service.LoginBoard.UserService;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    public Long id;
    public String name;
    public String email;

}
