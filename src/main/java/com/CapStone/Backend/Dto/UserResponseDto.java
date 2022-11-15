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
    public Long userId;
    public String userName;
    public String userEmail;

}
