package com.example.simpleprojects.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentsDto {
    private Integer studentId;
    private String lastName;
    private String firstName;
    private Integer groupId;

    private List<MarksDto> list;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
