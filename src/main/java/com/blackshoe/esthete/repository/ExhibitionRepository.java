package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition,Long> {
    Page<Exhibition> findAll(Pageable pageable);

    Page<Exhibition> findByTitleContaining(String title, Pageable pageable);

    Optional<Exhibition> findByExhibitionId(UUID exhibitionId);
}
