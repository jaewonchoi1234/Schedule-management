package com.sparta.schedulemanagement.Service;

import com.sparta.schedulemanagement.Dto.CommentRequestDto;
import com.sparta.schedulemanagement.Entity.Comment;
import com.sparta.schedulemanagement.Entity.Schedule;
import com.sparta.schedulemanagement.Repository.CommentRepository;
import com.sparta.schedulemanagement.Repository.ScheduleRepository;
import com.sparta.schedulemanagement.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final JwtUtil jwtUtil;

    //댓글등록
    public String createComment(CommentRequestDto commentRequestDto, String username) {

        Schedule schedule = scheduleRepository.findById(commentRequestDto.getScheduleId()).orElseThrow(
                ()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));

        Comment comment = new Comment(commentRequestDto.getComment(),username,schedule);
        Comment saveComment =commentRepository.save(comment);

        return saveComment.getComment();
    }

    //댓글수정
    @Transactional
    public String updateComment(CommentRequestDto commentRequestDto, String username) {

        scheduleRepository.findById(commentRequestDto.getScheduleId()).orElseThrow(()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));

        Comment comment = commentRepository.findById(commentRequestDto.getCommentId()).orElseThrow(()-> new NullPointerException("선택한 댓글은 저장되어 있지 않습니다."));

        if (!comment.getUserId().equals(username)) {
            throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
        }

        comment.setComment(commentRequestDto.getComment());

        return comment.getComment();
    }

    //댓글 삭제
    public String deleteComment(CommentRequestDto commentRequestDto, String username) {

        scheduleRepository.findById(commentRequestDto.getScheduleId()).orElseThrow(()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));

        Comment comment = commentRepository.findById(commentRequestDto.getCommentId()).orElseThrow(()-> new NullPointerException("선택한 댓글은 저장되어 있지 않습니다."));

        if (!comment.getUserId().equals(username)) {
            throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
        }

        commentRepository.delete(comment);

        return "삭제 성공!!";
    }



}
