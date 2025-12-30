package com.back.shared.post.dto;

import java.time.LocalDateTime;

public record PostCommentDto(int id, LocalDateTime createDate, LocalDateTime modifyDate
        , int authorId, String authorName, String comment) {
}