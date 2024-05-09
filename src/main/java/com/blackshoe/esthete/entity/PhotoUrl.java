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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photos_id", foreignKey = @ForeignKey(name = "photos_urls_fk_photos_id"))
    private Photo photo;

    @Column(name = "photos_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID photoUrlId;

    @Column(name = "img_url", nullable = false)
    private String cloudfrontUrl;

    @Column(name = "s3_url", nullable = false)
    private String s3Url;

    @Builder
    public PhotoUrl(Photo photo, String cloudfrontUrl, String s3Url) {
        this.photo = photo;
        this.photoUrlId = UUID.randomUUID();
        this.cloudfrontUrl = cloudfrontUrl;
        this.s3Url = s3Url;
    }
}