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
@Table(name = "photos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseEntity {
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

    @Column(name = "photos_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID photoId;

    @Column(name = "filters_uuid")
    private UUID filterId;

    @Column(name = "gray_scale")
    private Float grayScale;

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

    public void setTemporaryExhibition(TemporaryExhibition temporaryExhibition){
        this.temporaryExhibition = temporaryExhibition;
    }

    public void setExhibition(Exhibition exhibition){
        this.exhibition = exhibition;
    }

    public void deleteTemporaryExhibition(TemporaryExhibition temporaryExhibition){
        this.temporaryExhibition = null;
    }
}