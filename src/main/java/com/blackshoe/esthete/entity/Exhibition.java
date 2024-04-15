package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "exhibitions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitions_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "exhibitions_fk_users_id"))
    private User user;

    @Column(name = "exhibitions_uuid", nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID uuid;

    @Column(name = "thumbnail_url", nullable = false)
    private String cloudfrontUrl;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "view_count")
    private Long viewCount;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, length = 20)
    private LocalDateTime createdAt;

    public void increaseViewCount() {
        this.viewCount++;
    }
}
