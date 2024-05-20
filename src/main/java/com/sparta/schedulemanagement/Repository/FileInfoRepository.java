package com.sparta.schedulemanagement.Repository;

import com.sparta.schedulemanagement.Entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
