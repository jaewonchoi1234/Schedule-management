package com.sparta.schedulemanagement.Dto;

import com.sparta.schedulemanagement.Entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule Schedule) {
        this.id = Schedule.getId();
        this.title = Schedule.getTitle();
        this.contents = Schedule.getContents();
        this.manager = Schedule.getManager();
        this.password = Schedule.getPassword();
        this.createAt = Schedule.getCreatedAt();
        this.modifiedAt = Schedule.getModifiedAt();
    }
}
