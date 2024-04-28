package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter
@Table(name = "profile_urls")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_urls_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "profile_urls_fk_users_id"))
    private User user;

    @Column(name = "profile_urls_uuid", nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID profileUrlId;

    @Column(name = "img_url", nullable = false)
    private String cloudfrontUrl;

    @Column(name = "s3_url", nullable = false)
    private String s3Url;

    @Builder
    public ProfileUrl(User user, UUID profileUrlId, String cloudfrontUrl, String s3Url) {
        this.user = user;
        this.profileUrlId = profileUrlId;
        this.cloudfrontUrl = cloudfrontUrl;
        this.s3Url = s3Url;
    }

    public void updateProfileUrl(String cloudfrontUrl, String s3Url) {
        this.cloudfrontUrl = cloudfrontUrl;
        this.s3Url = s3Url;
    }
}
