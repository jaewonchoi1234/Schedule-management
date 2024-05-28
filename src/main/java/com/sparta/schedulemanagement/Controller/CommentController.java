package com.sparta.schedulemanagement.Controller;

import com.sparta.schedulemanagement.Dto.CommentRequestDto;
import com.sparta.schedulemanagement.Dto.CommentResponseDto;
import com.sparta.schedulemanagement.Service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/{scheduleId}")
    public String createComment(@PathVariable(required = true) Long scheduleId, @RequestBody CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getComment().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 비어 있습니다.");
        }
        return commentService.createComment(scheduleId, commentRequestDto);
    }

    @PutMapping("/comment/{scheduleId}/{commentId}")
    public String updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto ){
        return commentService.updateComment(scheduleId, commentId, commentRequestDto);
    }


}
