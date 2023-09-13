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
@Table(name = "mark")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer markId;
    private Integer subjectId;
    private Integer studentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
