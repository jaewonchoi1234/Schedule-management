package com.sparta.schedulemanagement.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    private String contents;

    @Email
    private String manager;

    @NotBlank
    private String password;

}
