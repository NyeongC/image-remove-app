package com.ccn.imageremove.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String nickname;

    @Builder
    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }
}