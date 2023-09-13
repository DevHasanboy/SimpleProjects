package com.example.simpleprojects.repository;

import com.example.simpleprojects.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MarksRepository extends JpaRepository<Marks,Integer> {
    Optional<Marks>findByMarkIdAndDeletedAtIsNull(Integer entityId);
}
