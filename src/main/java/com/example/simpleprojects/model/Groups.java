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
@Table(name = "groupTable")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;
    private String name;
    @OneToMany(mappedBy = "groupId",fetch = FetchType.EAGER)
    private List<Students> lists;
    @OneToMany(mappedBy = "groupId",fetch = FetchType.EAGER)
    private List<SubjTeach> subjTeachList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
