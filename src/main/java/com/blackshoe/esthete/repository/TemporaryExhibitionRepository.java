package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.TemporaryExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryExhibitionRepository extends JpaRepository<TemporaryExhibition,Long> {
}
