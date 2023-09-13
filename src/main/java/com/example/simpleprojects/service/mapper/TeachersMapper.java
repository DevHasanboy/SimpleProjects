package com.example.simpleprojects.service.mapper;

import com.example.simpleprojects.dto.TeachersDto;
import com.example.simpleprojects.model.Teachers;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TeachersMapper {
    @Autowired
    protected SubjTeachMapper subjTeachMapper;

@Mapping(target = "teacherId",ignore = true)
@Mapping(target = "createdAt",ignore = true)
@Mapping(target = "updatedAt",ignore = true)
@Mapping(target = "deletedAt",ignore = true)
@Mapping(target = "subjTeachList",ignore = true)
    public abstract Teachers toEntity(TeachersDto dto);

@Mapping(target = "subjTeachList",ignore = true)
    public abstract TeachersDto toDto(Teachers teachers);



@Mapping(target = "subjTeachList",expression = "java(teachers.getSubjTeachList().stream().map(this.subjTeachMapper::toDto).toList())")
public abstract TeachersDto toDtoWithSubjTeach1(Teachers teachers);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "subjTeachList",ignore = true)
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdate(@MappingTarget Teachers teachers, TeachersDto dto);
}
