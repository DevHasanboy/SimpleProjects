package com.example.simpleprojects.service.mapper;

import com.example.simpleprojects.dto.StudentsDto;
import com.example.simpleprojects.model.Students;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class StudentsMapper {
    @Autowired
    protected MarksMapper mapper;

    @Mapping(target = "studentId", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "list", ignore = true)
    public abstract Students toEntity(StudentsDto dto);

    @Mapping(target = "list", ignore = true)
    public abstract StudentsDto toDto(Students students);


    @Mapping(target = "list", expression = "java(students.getList().stream().map(this.mapper::toDto).toList())")
    public abstract StudentsDto toDtoWithMarks(Students students);

    @Mapping(target = "list", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdate(@MappingTarget Students students, StudentsDto dto);
}
