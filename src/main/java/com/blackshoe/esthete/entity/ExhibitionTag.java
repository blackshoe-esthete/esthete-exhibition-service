package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "exhibitions_tags")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitions_tags_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitions_id", foreignKey = @ForeignKey(name = "exhibitions_tags_fk_exhibitions_id"))
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "exhibitions_tags_fk_users_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tags_id", foreignKey = @ForeignKey(name = "exhibitions_tags_fk_tags_id"))
    private Tag tag;

    @Builder
    public ExhibitionTag(User user, Exhibition exhibition, Tag tag) {
        this.user = user;
        this.exhibition = exhibition;
        this.tag = tag;
    }
}
