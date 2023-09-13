package com.example.simpleprojects.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;
    private String title;

    @OneToMany(mappedBy = "subjectId",fetch = FetchType.EAGER)
    private List<SubjTeach> subjTeachList;
    @OneToMany(mappedBy = "subjectId",fetch = FetchType.EAGER)
    private List<Marks> marksList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
