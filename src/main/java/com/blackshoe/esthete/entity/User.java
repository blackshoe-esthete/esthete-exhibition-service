package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.constant.Gender;
import com.blackshoe.esthete.common.constant.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
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

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, length = 20)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", length = 20)
    private LocalDateTime updatedAt;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "support_count")
    private Long supportCount;

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
