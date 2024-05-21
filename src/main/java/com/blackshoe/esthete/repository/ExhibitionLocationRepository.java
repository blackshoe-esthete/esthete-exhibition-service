package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.ExhibitionLocation;
import com.blackshoe.esthete.entity.TemporaryExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExhibitionLocationRepository extends JpaRepository<ExhibitionLocation,Long> {
    Optional<ExhibitionLocation> findByTemporaryExhibition(TemporaryExhibition temporaryExhibition);

    Optional<ExhibitionLocation> findByExhibition(Exhibition exhibition);

    Boolean existsByTemporaryExhibition(TemporaryExhibition findTemporaryExhibition);
}