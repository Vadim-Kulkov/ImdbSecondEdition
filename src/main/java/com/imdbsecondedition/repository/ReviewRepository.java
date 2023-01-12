package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
