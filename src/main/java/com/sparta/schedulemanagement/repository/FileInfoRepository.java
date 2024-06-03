package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
