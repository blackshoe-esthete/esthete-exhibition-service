package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Photo;
import com.blackshoe.esthete.entity.TemporaryExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
    Optional<List<Photo>> findAllByTemporaryExhibition(TemporaryExhibition temporaryExhibition);

    Boolean existsAllByTemporaryExhibition(TemporaryExhibition findTemporaryExhibition);
}
