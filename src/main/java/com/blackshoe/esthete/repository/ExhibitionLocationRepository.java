package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.ExhibitionLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionLocationRepository extends JpaRepository<ExhibitionLocation,Long> {
}