package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.BaseEntity;
import com.blackshoe.esthete.common.constant.Gender;
import com.blackshoe.esthete.common.constant.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(name = "users_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID userId;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "introduce", length = 20)
    private String introduce;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "support_count")
    private Long supportCount;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileUrl profileUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follow> follows;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTag> userTags;

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseSupportCount() {
        this.supportCount++;
    }

    public void updateUserProfile(String nickname, String introduce, String biography) {
        this.nickname = nickname;
        this.introduce = introduce;
        this.biography = biography;
    }
}