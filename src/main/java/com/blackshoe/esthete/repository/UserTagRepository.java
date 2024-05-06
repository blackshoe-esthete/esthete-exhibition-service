package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag,Long> {
    void deleteAllByUser(User user);
    List<UserTag> findAllByUser(User user);
}