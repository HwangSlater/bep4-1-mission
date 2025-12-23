package com.back.boundedContext.member.entity;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member extends BaseIdAndTime {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    @Column(nullable = false)
    private int actionScore;

    public Member(String username, String password, String nickname, int actionScore) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.actionScore = actionScore;
    }

    public Member(String username, String password, String nickname) {
        this(username, password, nickname, 0);
    }

    public void addActionScore(int score) {
        actionScore += score;
    }
}