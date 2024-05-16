package com.sparta.schedulemanagement.Entity;


import com.sparta.schedulemanagement.Dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    public Schedule(ScheduleRequestDto requestDto) {
        this.id = requestDto.getId();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}
