package com.example.simpleprojects.repository;

import com.example.simpleprojects.model.SubjTeach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SubjTeachRepository extends JpaRepository<SubjTeach,Integer> {
    Optional<SubjTeach> findBySubjectIdAndDeletedAtIsNull(Integer entityId);
}
