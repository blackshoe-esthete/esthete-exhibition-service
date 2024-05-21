package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "photos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photos_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitions_id", foreignKey = @ForeignKey(name = "photos_fk_exhibitions_id"))
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_exhibitions_id", foreignKey = @ForeignKey(name = "photos_fk_temporary_exhibitions_id"))
    private TemporaryExhibition temporaryExhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "photos_fk_users_id"))
    private User user;

    @Column(name = "photos_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID photoId;

    @Column(name = "gray_scale")
    private Float grayScale;

    @Column(name = "filter_uuid")
    private UUID filterId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, length = 20)
    private LocalDateTime createdAt;

    @PrePersist
    public void updatePhotoId() {
        if (this.photoId == null) {
            this.photoId = UUID.randomUUID();
        }
    }

    @Builder
    public Photo(Float grayScale, UUID filterId){
        this.grayScale = grayScale;
        this.filterId = filterId;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTemporaryExhibition(TemporaryExhibition temporaryExhibition){
        this.temporaryExhibition = temporaryExhibition;
    }

    public void setExhibition(Exhibition exhibition){
        this.exhibition = exhibition;
    }

    public void deleteTemporaryExhibition(TemporaryExhibition temporaryExhibition){
        this.temporaryExhibition = null;
    }

//    public void updateTemporaryExhibition(TemporaryExhibition temporaryExhibition){
//        this.temporaryExhibition = temporaryExhibition;
//        temporaryExhibition.addTemporaryExhibitionPhoto(this);
//    }
//
//    public void updateExhibition(Exhibition exhibition){
//        this.exhibition = exhibition;
//        exhibition.addTemporaryExhibitionPhoto(this);
//    }
//
//    public void updateExhibition(Exhibition exhibition){
//        this.exhibition = exhibition;
//        exhibition.addPhoto(this);
//    }
}