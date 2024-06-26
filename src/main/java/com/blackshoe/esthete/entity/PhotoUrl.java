package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "photos_urls")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photos_urls_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photos_id", foreignKey = @ForeignKey(name = "photos_urls_fk_photos_id"))
    private Photo photo;

    @Column(name = "photos_urls_uuid", columnDefinition = "BINARY(16)")
    private UUID photoUrlId;

    @Column(name = "img_url", nullable = false)
    private String cloudfrontUrl;

    @Column(name = "s3_url", nullable = false)
    private String s3Url;

    @PrePersist
    public void updatePhotoUrlId() {
        if (this.photoUrlId == null) {
            this.photoUrlId = UUID.randomUUID();
        }
    }

    @Builder
    public PhotoUrl(String cloudfrontUrl, String s3Url) {
        this.cloudfrontUrl = cloudfrontUrl;
        this.s3Url = s3Url;
    }

    public void setPhoto(Photo photo){
        this.photo = photo;
    }
}