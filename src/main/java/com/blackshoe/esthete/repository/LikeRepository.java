package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByUserIdAndExhibitionId(UUID userId, UUID exhibitionId);
}
