package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition,Long> {
}
