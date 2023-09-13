package com.example.simpleprojects.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subj")
public class SubjTeach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectTeachId;
    private Integer groupId;
    private Integer subjectId;
    private Integer teacherId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
