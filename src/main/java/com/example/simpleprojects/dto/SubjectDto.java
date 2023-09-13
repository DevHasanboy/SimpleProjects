package com.example.simpleprojects.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
    private Integer subjectId;
    private String title;

    private List<SubjDto> subjTeachList;
    private List<MarksDto> marksList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
