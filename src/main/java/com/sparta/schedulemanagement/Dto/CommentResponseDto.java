package com.sparta.schedulemanagement.Dto;


import com.sparta.schedulemanagement.Entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String userId;
    private Schedule schedule;
}
