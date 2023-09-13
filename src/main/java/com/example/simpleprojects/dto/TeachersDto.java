package com.example.simpleprojects.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachersDto {
    private Integer teacherId;
    private String lastName;
    private String firstName;

    private List<SubjDto> subjTeachList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
