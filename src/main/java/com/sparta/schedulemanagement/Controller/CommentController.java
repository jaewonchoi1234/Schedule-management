package com.sparta.schedulemanagement.Controller;

import com.sparta.schedulemanagement.Dto.CommentRequestDto;
import com.sparta.schedulemanagement.Service.CommentService;
import com.sparta.schedulemanagement.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.sparta.schedulemanagement.jwt.JwtUtil;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public String createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @Validated(CommentRequestDto.CreateGroup.class) @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto,userDetails.getUsername());
    }

    @PutMapping("/comment")
    public String updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @Validated(CommentRequestDto.UpdateGroup.class) @RequestBody CommentRequestDto commentRequestDto ){
        return commentService.updateComment(commentRequestDto,userDetails.getUsername());
    }

    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails,@Validated(CommentRequestDto.DeleteGroup.class) @RequestBody CommentRequestDto commentRequestDto ) {
        return ResponseEntity.ok(commentService.deleteComment(commentRequestDto, userDetails.getUsername()));
    }



}
