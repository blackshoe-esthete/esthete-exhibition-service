package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "temporary_exhibitions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporaryExhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temporary_exhibitions_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "temporary_exhibitions_fk_users_id"))
    private User user;

    @Column(name = "temporary_exhibitions_uuid", columnDefinition = "BINARY(16)", unique = true)
    private UUID temporaryExhibitionId;

    @Column(name = "thumbnail_url", nullable = false)
    private String cloudfrontUrl;

    @OneToMany(mappedBy = "temporaryExhibition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemporaryExhibitionPhoto> photos;
}