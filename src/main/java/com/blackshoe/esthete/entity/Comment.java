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
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitions_id", foreignKey = @ForeignKey(name = "comments_fk_exhibitions_id"))
    private Exhibition exhibition;

    @Column(columnDefinition = "BINARY(16)", name = "users_uuid", nullable = false)
    private UUID userId;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Column(name = "is_like", nullable = false)
    private Boolean isLike;

    @Builder
    public Comment(Exhibition exhibition, UUID userId, String content) {
        this.exhibition = exhibition;
        this.userId = userId;
        this.content = content;
        this.isLike = false;
    }

    public void addLike() {
        this.isLike = true;
    }
}
