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
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "photos_fk_users_id"))
    private User user;

    @Column(name = "photos_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID photoId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, length = 20)
    private LocalDateTime createdAt;

    @Builder
    Photo(Exhibition exhibition, User user) {
        this.exhibition = exhibition;
        this.user = user;
        this.photoId = UUID.randomUUID();
    }
}