package com.sparta.schedulemanagement.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.sparta.schedulemanagement.entity.FileInfo;

@Getter
@Setter
@NoArgsConstructor
public class FileInfoResponseDto {
    private Long id;
    private String title;
    private String path;

    public FileInfoResponseDto(FileInfo fileInfo) {
        this.id = fileInfo.getId();
        this.title = fileInfo.getTitle();
        this.path = fileInfo.getPath();
    }
}
