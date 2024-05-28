package com.sparta.schedulemanagement.Service;

import com.sparta.schedulemanagement.Dto.CommentRequestDto;
import com.sparta.schedulemanagement.Entity.Comment;
import com.sparta.schedulemanagement.Entity.Schedule;
import com.sparta.schedulemanagement.Repository.CommentRepository;
import com.sparta.schedulemanagement.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    //댓글등록
    public String createComment(Long scheduleId, CommentRequestDto commentRequestDto) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));

        Comment comment = new Comment(commentRequestDto,schedule);
        Comment saveComment =commentRepository.save(comment);

        return saveComment.getComment();
    }

    //댓글수정
    @Transactional
    public String updateComment(Long scheduleId, Long commentId, CommentRequestDto commentRequestDto) {

        scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException("선택한 댓글은 저장되어 있지 않습니다."));

        if (!comment.getUserId().equals(commentRequestDto.getUserId())) {
            throw new IllegalArgumentException("선택한 댓글의 사용자가 현재 사용자와 일치하지 않습니다.");
        }

        comment.setComment(commentRequestDto.getComment());

        return comment.getComment();
    }



}
