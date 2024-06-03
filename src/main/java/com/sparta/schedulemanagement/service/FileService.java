package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.FileInfoResponseDto;
import com.sparta.schedulemanagement.entity.FileInfo;
import com.sparta.schedulemanagement.repository.FileInfoRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final FileInfoRepository fileInfoRepository;
    //이미지 저장 경로
    private final String path = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\";

    public FileService(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    //이미지 업로드
    public FileInfoResponseDto imageUpload(MultipartFile file) throws IOException {
        //이미지 확인
        if (!file.getContentType().contains("image")) {
            throw new IllegalArgumentException("이미지 파일이 아닙니다.");
        }
        //이미지 객체 및 디렉토리 생성
        File destFile = new File(path+file.getOriginalFilename());
        destFile.mkdirs();
        //이미지 저장
        file.transferTo(destFile);
        //DB에 파일 정보 저장
        FileInfo fileInfo = new FileInfo(destFile.getName(),destFile.getPath());
        return new FileInfoResponseDto(fileInfoRepository.save(fileInfo));
    }


    //이미지 다운로드
    public ResponseEntity<Resource> imageDownload(String filename) throws IOException {
        try {
            Path filePath = Paths.get(path+filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error: " + ex.getMessage());
        }

    }
}
