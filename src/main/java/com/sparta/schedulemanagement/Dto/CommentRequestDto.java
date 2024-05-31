package com.sparta.schedulemanagement.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {

    @NotNull(groups={CreateGroup.class, UpdateGroup.class,DeleteGroup.class})
    private Long scheduleId;
    @NotNull(groups={UpdateGroup.class, DeleteGroup.class})
    private Long commentId;
    @NotBlank(groups={CreateGroup.class, UpdateGroup.class})
    private String comment;

    public interface CreateGroup{}
    public interface UpdateGroup{}
    public interface DeleteGroup{}
}

