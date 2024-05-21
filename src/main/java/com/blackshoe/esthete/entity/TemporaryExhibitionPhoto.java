//package com.blackshoe.esthete.entity;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Getter
//@Table(name = "temporary_exhibitions_photos")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class TemporaryExhibitionPhoto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "temporary_exhibitions_photos_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "temporary_exhibitions_id", foreignKey = @ForeignKey(name = "temporary_exhibitions_photos_fk_temporary_exhibitions_id"))
//    private TemporaryExhibition temporaryExhibition;
//
//    @Column(name = "img_url")
//    private String cloudfrontUrl;
//
//    @Column(name = "s3_url")
//    private String s3Url;
//
//    @Column(name = "gray_scale")
//    private Float grayScale;
//
//    @Column(name = "filter_uuid")
//    private UUID filterId;
//
//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, length = 20)
//    private LocalDateTime createdAt;
//
//    @Builder
//    public TemporaryExhibitionPhoto(Float grayScale, UUID filterId, String s3Url, String cloudfrontUrl){
//        this.grayScale = grayScale;
//        this.filterId = filterId;
//        this.s3Url = s3Url;
//        this.cloudfrontUrl = cloudfrontUrl;
//    }
//
//    public void updateTemporaryExhibition(TemporaryExhibition temporaryExhibition){
//        this.temporaryExhibition = temporaryExhibition;
//        temporaryExhibition.addTemporaryExhibitionPhoto(this);
//    }
//
//    public void saveGrayScaleAndFilterId(Float grayScale, UUID filterId){
//        this.grayScale = grayScale;
//        this.filterId = filterId;
//    }
//}