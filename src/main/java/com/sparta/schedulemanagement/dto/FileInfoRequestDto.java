package com.sparta.schedulemanagement.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileInfoRequestDto {
    private Long id;
    private String title;
    private String path;
}
