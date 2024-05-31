package com.sparta.schedulemanagement.Dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {


    private String nickname;

    @Size(min=4,max=10)
    @Pattern(regexp="[a-z0-9]+")
    private String username;

    @Size(min=8,max=15)
    @Pattern(regexp="[a-zA-Z0-9]+")
    private String password;

    private String adminToken = "";


}
