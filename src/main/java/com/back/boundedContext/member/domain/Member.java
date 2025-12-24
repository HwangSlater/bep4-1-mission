package com.back.boundedContext.member.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
public class Member extends BaseIdAndTime {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    @Column(nullable = false)
    private int activityScore;

    public Member(String username, String password, String nickname, int activityScore) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.activityScore = activityScore;
    }

    public Member(String username, String password, String nickname) {
        this(username, password, nickname, 0);
    }

    public void increaseActivityScore(int score) {
        activityScore += score;
    }
}