package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View,Long>  {
    Optional<List<View>> findAllByExhibition(Exhibition exhibition);
}
