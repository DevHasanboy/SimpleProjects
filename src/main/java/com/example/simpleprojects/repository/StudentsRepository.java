package com.example.simpleprojects.repository;

import com.example.simpleprojects.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentsRepository extends JpaRepository<Students,Integer> {
    Optional<Students> findByGroupIdAndDeletedAtIsNull(Integer entityId);
}
