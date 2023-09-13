package com.example.simpleprojects.repository;

import com.example.simpleprojects.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SubjectRepository extends JpaRepository<Subjects,Integer> {
    Optional<Subjects>findBySubjectIdAndDeletedAtIsNull(Integer entityId);
}
