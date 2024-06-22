package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @Column(columnDefinition = "BINARY(16)", name = "users_uuid", nullable = false)
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)", name = "exhibitions_uuid", nullable = false)
    private UUID exhibitionId;

    @Builder
    public Like(UUID userId, UUID exhibitionId) {
        this.userId = userId;
        this.exhibitionId = exhibitionId;
    }
}