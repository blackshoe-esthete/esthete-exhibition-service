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
@Table(name = "exhibitions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exhibition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitions_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "exhibitions_fk_users_id"))
    private User user;

    @Column(name = "exhibitions_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID exhibitionId;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "view_count")
    private Long viewCount;

    @PrePersist
    public void updateExhibitionId() {
        if (this.exhibitionId == null) {
            this.exhibitionId = UUID.randomUUID();
        }
    }

    @Builder
    public Exhibition(UUID exhibitionId, String thumbnailUrl, String title, String description) {
        this.exhibitionId = exhibitionId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.viewCount = 0L;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void updateExhibitionInfo(String title, String description){
        this.title = title;
        this.description = description;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
}