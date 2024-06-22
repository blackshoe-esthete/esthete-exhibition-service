package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Follow;
import com.blackshoe.esthete.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {
    boolean existsByUserAndFollowerId(User user, UUID userId);

    Optional<Follow> findByUserAndFollowerId(User user, UUID followerId);
}
