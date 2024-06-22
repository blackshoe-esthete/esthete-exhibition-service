package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findByUserId(UUID userId);
    Page<User> findByNicknameContaining(String nickname, PageRequest pageRequest);
    @Query("SELECT u FROM User u WHERE u.userId IN (SELECT f.followerId FROM Follow f WHERE f.user = :user) AND u.nickname LIKE %:keyword%")
    List<User> findFollowersByUserAndKeyword(User user, String keyword);
}
