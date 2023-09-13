package com.example.simpleprojects.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjDto {
    private Integer subjectTeachId;
    private Integer groupId;
    private Integer subjectId;
    private Integer teacherId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
