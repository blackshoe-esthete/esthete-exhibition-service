package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.WithdrawReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawReasonRepository extends JpaRepository<WithdrawReason,Long> {
}
