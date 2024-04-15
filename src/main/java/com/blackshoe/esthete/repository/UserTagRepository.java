package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag,Long>  {
}