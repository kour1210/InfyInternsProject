package com.infy.infyinterns.repository;

import com.infy.infyinterns.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {
    List<Mentor> findByNumberOfProjectsMentored(Integer numberOfProjectsMentored);
}
