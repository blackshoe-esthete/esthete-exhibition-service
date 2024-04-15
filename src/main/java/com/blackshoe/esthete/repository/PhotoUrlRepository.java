package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.PhotoUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoUrlRepository extends JpaRepository<PhotoUrl,Long> {
}