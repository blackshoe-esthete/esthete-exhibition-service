package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.ExhibitionEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionEquipmentRepository extends JpaRepository<ExhibitionEquipment,Long> {
}
