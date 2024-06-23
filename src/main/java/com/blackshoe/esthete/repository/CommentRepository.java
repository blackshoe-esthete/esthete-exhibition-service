package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.Comment;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.TemporaryExhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByExhibition(Exhibition exhibition);

    Optional<Comment> findByCommentId(UUID commentId);
}