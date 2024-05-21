package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.ExhibitionTag;
import com.blackshoe.esthete.entity.TemporaryExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag,Long> {
    Boolean existsAllByTemporaryExhibition(TemporaryExhibition findTemporaryExhibition);

    Optional<List<ExhibitionTag>> findAllByTemporaryExhibition(TemporaryExhibition findTemporaryExhibition);
}