package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.CommentRepository;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    //댓글등록
    public String createComment(CommentRequestDto commentRequestDto, String username, Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        Comment comment = new Comment(commentRequestDto.getComment(),username,schedule);
        Comment saveComment =commentRepository.save(comment);
        return saveComment.getComment();
    }

    //댓글수정
    @Transactional
    public String updateComment(CommentRequestDto commentRequestDto, String username, Long scheduleId, Long commentId) {
        findScheduleById(scheduleId);
        Comment comment = findCommentById(commentId);
        validateCommentUser(comment, username);
        comment.setComment(commentRequestDto.getComment());
        return comment.getComment();
    }

    //댓글 삭제
    public String deleteComment(String username, Long scheduleId, Long commentId) {
        findScheduleById(scheduleId);
        Comment comment = findCommentById(commentId);
        validateCommentUser(comment, username);
        commentRepository.delete(comment);
        return "삭제 성공!!";
    }



    //중복 함수
    public Schedule findScheduleById(Long scheduleId){
        return scheduleRepository.findById(scheduleId).orElseThrow(()-> new NullPointerException("선택한 일정은 삭제되어 있습니다."));
    }
    public Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(()-> new NullPointerException("선택한 댓글은 삭제되어 있습니다."));
    }
    public void validateCommentUser(Comment comment, String username) {
        if (!comment.getUserId().equals(username)) {
            throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
        }
    }
}
