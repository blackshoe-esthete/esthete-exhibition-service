package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "profile_urls")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_urls_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "profile_urls_fk_users_id"))
    private User user;

    @Column(name = "img_url", nullable = false)
    private String cloudfrontUrl;

    @Column(name = "s3_url", nullable = false)
    private String s3Url;

    @Builder
    public ProfileUrl(User user, String cloudfrontUrl, String s3Url) {
        this.user = user;
        this.cloudfrontUrl = cloudfrontUrl;
        this.s3Url = s3Url;
    }

    public void updateProfileUrl(String cloudfrontUrl, String s3Url) {
        this.cloudfrontUrl = cloudfrontUrl;
        this.s3Url = s3Url;
    }
}
