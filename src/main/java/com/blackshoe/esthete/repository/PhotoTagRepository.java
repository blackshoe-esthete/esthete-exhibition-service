package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.PhotoTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoTagRepository extends JpaRepository<PhotoTag,Long> {
}
