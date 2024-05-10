package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition,Long> {
    Page<Exhibition> findAll(Pageable pageable);

    Page<Exhibition> findByTitleContaining(String title, Pageable pageable);
}
