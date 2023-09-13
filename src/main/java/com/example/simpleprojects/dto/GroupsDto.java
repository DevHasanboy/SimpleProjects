package com.example.simpleprojects.dto;

import com.example.simpleprojects.model.Students;
import com.example.simpleprojects.model.SubjTeach;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupsDto {
    private Integer groupId;
    private String name;

    private List<StudentsDto> lists;
    private List<SubjDto> subjTeachList;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}
