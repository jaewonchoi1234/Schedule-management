package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/{scheduleId}")
    public String createComment(@AuthenticationPrincipal UserDetails userDetails, @Validated @RequestBody CommentRequestDto requestDto, @PathVariable Long scheduleId) {
        return commentService.createComment(requestDto,userDetails.getUsername(),scheduleId);
    }

    @PutMapping("/comment/{scheduleId}/{commentId}")
    public String updateComment(@AuthenticationPrincipal UserDetails userDetails, @Validated @RequestBody CommentRequestDto commentRequestDto, @PathVariable Long scheduleId, @PathVariable Long commentId ){
        return commentService.updateComment(commentRequestDto,userDetails.getUsername(),scheduleId,commentId);
    }

    @DeleteMapping("/comment/{scheduleId}/{commentId}")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long scheduleId,@PathVariable Long commentId ) {
        return ResponseEntity.ok(commentService.deleteComment(userDetails.getUsername(),scheduleId,commentId));
    }





}
