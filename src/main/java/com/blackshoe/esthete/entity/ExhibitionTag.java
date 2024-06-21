package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
    @JoinColumn(name = "tags_id", foreignKey = @ForeignKey(name = "exhibitions_tags_fk_tags_id"))
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_exhibitions_id", foreignKey = @ForeignKey(name = "exhibitions_tags_fk_temporary_exhibitions_id"))
    private TemporaryExhibition temporaryExhibition;

    @Builder
    public ExhibitionTag(Exhibition exhibition, Tag tag, TemporaryExhibition temporaryExhibition) {
        this.exhibition = exhibition;
        this.tag = tag;
        this.temporaryExhibition = temporaryExhibition;
    }

    public void updateTemporaryExhibition(TemporaryExhibition temporaryExhibition){
        this.temporaryExhibition = temporaryExhibition;
    }

    public void updateExhibition(Exhibition exhibition){
        this.exhibition = exhibition;
    }

    public void updateTag(Tag tag){
        this.tag = tag;
        tag.getExhibitionTags().add(this);
    }

    public void deleteTemporaryExhibition(){
        this.temporaryExhibition = null;
    }
}