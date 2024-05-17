package com.sparta.schedulemanagement.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;

}
