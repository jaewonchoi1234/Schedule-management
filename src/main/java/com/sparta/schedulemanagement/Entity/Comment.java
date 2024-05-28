package com.sparta.schedulemanagement.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="Comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="comment", nullable=false)
    private String comment;

    @Column(name="user_id", nullable=false)
    private String user_id;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

}
