package com.sparta.schedulemanagement.Repository;

import com.sparta.schedulemanagement.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
