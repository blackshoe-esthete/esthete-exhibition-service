package com.blackshoe.esthete.repository;


import com.blackshoe.esthete.entity.Comment;
import com.blackshoe.esthete.entity.DeleteReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteReasonRepository extends JpaRepository<DeleteReason,Long> {
}
