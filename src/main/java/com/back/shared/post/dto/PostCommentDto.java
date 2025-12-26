package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(int id, LocalDateTime createDate, LocalDateTime modifyDate
        , int authorId, String authorName, String comment) {
    public PostCommentDto(PostComment postComment) {
        this(
                postComment.getId(),
                postComment.getCreateDate(),
                postComment.getModifyDate(),
                postComment.getAuthor().getId(),
                postComment.getAuthor().getNickname(),
                postComment.getContent()
        );
    }
}