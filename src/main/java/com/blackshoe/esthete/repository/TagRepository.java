package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query("SELECT u FROM Tag u WHERE u.name = :name")
    Optional<Tag> findByName(String name);
}