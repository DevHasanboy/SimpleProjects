package com.example.simpleprojects.repository;

import com.example.simpleprojects.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    Optional<Groups> findByGroupIdAndDeletedAtIsNull(Integer entityId);
}
