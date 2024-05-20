package com.sparta.schedulemanagement.Controller;

import com.sparta.schedulemanagement.Dto.FileInfoRequestDto;
import com.sparta.schedulemanagement.Dto.FileInfoResponseDto;
import com.sparta.schedulemanagement.Service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value="/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileInfoResponseDto imageUpload(@RequestParam("image") MultipartFile file) throws IOException {
        return fileService.imageUpload(file);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> imageDownload(@PathVariable String filename) throws IOException {
        return fileService.imageDownload(filename);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleCustomException(IOException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
