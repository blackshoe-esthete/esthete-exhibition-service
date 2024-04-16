package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.entity.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook,Long> {
}
