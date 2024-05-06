package com.blackshoe.esthete.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "exhibition_locations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_locations_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibitions_id", foreignKey = @ForeignKey(name = "exhibition_locations_fk_exhibitions_id"))
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", foreignKey = @ForeignKey(name = "exhibition_locations_fk_users_id"))
    private User user;

    @Column(name = "longitude", nullable = false, length = 20)
    private String longitude;

    @Column(name = "latitude", nullable = false, length = 20)
    private String latitude;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Column(name = "town", nullable = false, length = 20)
    private String town;
}
