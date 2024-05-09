package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "temporary_exhibitions_photos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporaryExhibitionPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temporary_exhibitions_photos_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_exhibitions_id", foreignKey = @ForeignKey(name = "temporary_exhibitions_photos_fk_temporary_exhibitions_id"))
    private TemporaryExhibition temporaryExhibition;

    @Column(name = "img_url", nullable = false)
    private String cloudfrontUrl;

    @Column(name = "s3_url", nullable = false)
    private String s3Url;
}