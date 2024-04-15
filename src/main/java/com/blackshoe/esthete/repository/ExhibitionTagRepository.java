package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.ExhibitionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionTagRepository extends JpaRepository<ExhibitionTag,Long> {
}