package com.example.simpleprojects.repository;

import com.example.simpleprojects.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TeachersRepository extends JpaRepository<Teachers,Integer> {
    Optional<Teachers> findByTeacherIdAndDeletedAtIsNull(Integer entityId);
}
