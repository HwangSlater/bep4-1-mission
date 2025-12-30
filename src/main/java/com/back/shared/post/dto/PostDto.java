package com.back.shared.post.dto;

import com.back.standard.modelType.CanGetModelTypeCode;

import java.time.LocalDateTime;

public record PostDto(int id, LocalDateTime createDate, LocalDateTime modifyDate,
                      int authorId, String authorName, String title, String content) implements CanGetModelTypeCode {

    @Override
    public String getModelTypeCode() {
        return "Post";
    }
}