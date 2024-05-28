package com.sparta.schedulemanagement.Entity;


import com.sparta.schedulemanagement.Dto.CommentRequestDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="Comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    public Comment(CommentRequestDto requestDto, Schedule schedule) {
            this.comment = requestDto.getComment();
            this.userId = requestDto.getUserId();
            this.schedule = schedule;
    }
}
