package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "guest_books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_books_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitions_id", foreignKey = @ForeignKey(name = "guest_books_fk_exhibitions_id"))
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "guest_books_fk_users_id"))
    private User user;

    @Column(columnDefinition = "BINARY(16)", name = "guest_uuid", nullable = false)
    private UUID guestUuid;

    @Column(name = "content", nullable = false, length = 50)
    private String content;

    @Column(name = "is_like", nullable = false, length = 50)
    private Boolean isLike;
}
