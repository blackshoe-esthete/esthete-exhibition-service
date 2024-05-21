package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Photo;
import com.blackshoe.esthete.entity.PhotoUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoUrlRepository extends JpaRepository<PhotoUrl,Long> {
    Optional<PhotoUrl> findByPhoto(Photo photo);

}