package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View,Long>  {
}
