package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users_tags")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_tags_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "users_tags_fk_users_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags_id", foreignKey = @ForeignKey(name = "users_tags_fk_tags_id"))
    private Tag tag;

    @Builder
    public UserTag(User user, Tag tag) {
        this.user = user;
        this.tag = tag;
    }
}