package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.ProfileUrl;
import com.blackshoe.esthete.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileUrlRepository extends JpaRepository<ProfileUrl,Long> {
    @Query("SELECT u FROM ProfileUrl u WHERE u.user = :user")
    ProfileUrl findByUser(User user);
}
