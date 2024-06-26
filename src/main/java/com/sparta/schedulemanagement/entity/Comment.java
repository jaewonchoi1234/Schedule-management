package com.sparta.schedulemanagement.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
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


    public Comment(String comment, String userId, Schedule schedule) {
            this.comment = comment;
            this.userId = userId;
            this.schedule = schedule;
    }

}
