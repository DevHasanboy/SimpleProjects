package com.example.simpleprojects.service.mapper;


import com.example.simpleprojects.dto.SubjectDto;

import com.example.simpleprojects.model.Subjects;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class SubjectsMapper {
    @Autowired
    protected MarksMapper mapper;
    @Autowired
    protected SubjTeachMapper subjTeachMapper;
    @Mapping(target = "subjectId",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "subjTeachList",ignore = true)
    @Mapping(target = "marksList",ignore = true)
    public abstract Subjects toEntity(SubjectDto dto);


    @Mapping(target = "subjTeachList",ignore = true)
    @Mapping(target = "marksList",ignore = true)
    public abstract SubjectDto toDto(Subjects subjects);


    @Mapping(target = "subjTeachList",ignore = true)
    @Mapping(target = "marksList",expression = "java(subjects.getMarksList().stream().map(this.mapper::toDto).toList())")
    public abstract SubjectDto toDtoWithMArks(Subjects subjects);



    @Mapping(target = "marksList",ignore = true)
    @Mapping(target = "subjTeachList",expression = "java(subjects.getSubjTeachList().stream().map(this.subjTeachMapper::toDto).toList())")
    public abstract SubjectDto toDtoWithSubjTeach(Subjects subjects);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "marksList",ignore = true)
    @Mapping(target = "subjTeachList",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdate(@MappingTarget Subjects subjects, SubjectDto dto);
}
