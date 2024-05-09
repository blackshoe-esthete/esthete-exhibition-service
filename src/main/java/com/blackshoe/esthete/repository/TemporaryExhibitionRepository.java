package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.TemporaryExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemporaryExhibitionRepository extends JpaRepository<TemporaryExhibition,Long> {
    @Query("SELECT u FROM TemporaryExhibition u WHERE u.temporaryExhibitionId = :temporaryExhibitionId")
    Optional<TemporaryExhibition> findByTemporaryExhibitionId(UUID temporaryExhibitionId);
}
