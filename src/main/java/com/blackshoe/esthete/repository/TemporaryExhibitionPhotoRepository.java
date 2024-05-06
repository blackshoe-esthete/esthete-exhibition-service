package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.TemporaryExhibition;
import com.blackshoe.esthete.entity.TemporaryExhibitionPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporaryExhibitionPhotoRepository extends JpaRepository<TemporaryExhibitionPhoto,Long> {
    List<TemporaryExhibitionPhoto> findAllByTemporaryExhibition(TemporaryExhibition temporaryExhibition);
}