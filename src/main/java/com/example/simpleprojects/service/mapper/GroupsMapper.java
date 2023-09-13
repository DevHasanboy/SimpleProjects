package com.example.simpleprojects.service.mapper;

import com.example.simpleprojects.dto.GroupsDto;
import com.example.simpleprojects.model.Groups;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class GroupsMapper {
    @Autowired
    protected StudentsMapper studentsMapper;
    @Autowired
    protected SubjTeachMapper subjTeachMapper;

    @Mapping(target = "groupId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "lists", ignore = true)
    @Mapping(target = "subjTeachList", ignore = true)
    public abstract Groups toEntity(GroupsDto dto);


    @Mapping(target = "lists", ignore = true)
    @Mapping(target = "subjTeachList", ignore = true)
    public abstract GroupsDto toDto(Groups groups);


    @Mapping(target = "subjTeachList", ignore = true)
    @Mapping(target = "lists", expression = "java(groups.getLists().stream().map(this.studentsMapper::toDto).toList())")
    public abstract GroupsDto toDtoWithStudent(Groups groups);

    @Mapping(target = "lists", ignore = true)
    @Mapping(target = "subjTeachList", expression = "java(groups.getSubjTeachList().stream().map(this.subjTeachMapper::toDto).toList())")
    public abstract GroupsDto toDtoWithSubjTeach(Groups groups);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "lists", ignore = true)
    @Mapping(target = "subjTeachList", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdate(@MappingTarget Groups groups, GroupsDto dto);
}
