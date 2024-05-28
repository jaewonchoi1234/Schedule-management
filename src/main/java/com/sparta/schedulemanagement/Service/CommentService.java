package com.sparta.schedulemanagement.Service;

import com.sparta.schedulemanagement.Dto.CommentRequestDto;
import com.sparta.schedulemanagement.Dto.CommentResponseDto;
import com.sparta.schedulemanagement.Entity.Comment;
import com.sparta.schedulemanagement.Entity.Schedule;
import com.sparta.schedulemanagement.Repository.CommentRepository;
import com.sparta.schedulemanagement.Repository.FileInfoRepository;
import com.sparta.schedulemanagement.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    public String createComment(Long scheduleId, CommentRequestDto commentRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));

        Comment comment = new Comment(commentRequestDto,schedule);
        Comment saveComment =commentRepository.save(comment);

        return saveComment.getComment();
    }


}
