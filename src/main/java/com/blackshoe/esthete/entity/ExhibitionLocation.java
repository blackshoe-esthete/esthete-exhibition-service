package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "exhibitions_locations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibitions_locations_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitions_id", foreignKey = @ForeignKey(name = "exhibitions_locations_fk_exhibitions_id"))
    private Exhibition exhibition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "temporary_exhibitions_id", foreignKey = @ForeignKey(name = "exhibitions_locations_fk_exhibitions_id"))
    private TemporaryExhibition temporaryExhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "exhibitions_locations_fk_users_id"))
    private User user;

    @Column(name = "longitude", nullable = false, length = 20)
    private Double longitude;

    @Column(name = "latitude", nullable = false, length = 20)
    private Double latitude;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "town", nullable = false, length = 100)
    private String town;

    @Builder
    public ExhibitionLocation(Double longitude, Double latitude, String state, String city, String town) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.city = city;
        this.town = town;
    }

    public void changeExhibitionLocation(Double longitude, Double latitude, String state, String city, String town){
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.city = city;
        this.town = town;
    }

    public void updateTemporaryExhibition(TemporaryExhibition temporaryExhibition){
        this.temporaryExhibition = temporaryExhibition;
    }

    public void deleteTemporaryExhibition(){
        this.temporaryExhibition = null;
    }

    public void updateExhibition(Exhibition exhibition){
        this.exhibition = exhibition;
    }


}