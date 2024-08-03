package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "temporary_exhibitions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporaryExhibition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temporary_exhibitions_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "temporary_exhibitions_fk_users_id"))
    private User user;

    @Column(name = "temporary_exhibitions_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID temporaryExhibitionId;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "view_count")
    private Long viewCount;

    @OneToOne(mappedBy = "temporaryExhibition", cascade = CascadeType.ALL, orphanRemoval = false)
    private ExhibitionLocation exhibitionLocation;

    @OneToMany(mappedBy = "temporaryExhibition", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ExhibitionTag> exhibitionTags;

    @OneToMany(mappedBy = "temporaryExhibition", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Photo> photos;

    @PrePersist
    public void updateTemporaryExhibitionId() {
        if (this.temporaryExhibitionId == null) {
            this.temporaryExhibitionId = UUID.randomUUID();
        }
    }

    @Builder
    public TemporaryExhibition(String thumbnailUrl, String title, String description) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.viewCount = 0L;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void deleteUser(){
        this.user = null;
    }

    public void updateTemporaryExhibitionInfo(String title, String description){
        this.title = title;
        this.description = description;
    }

    public void setCloudfrontUrl(String thumbnailUrl) {this.thumbnailUrl = thumbnailUrl;}
}