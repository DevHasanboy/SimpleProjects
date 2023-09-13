package com.example.simpleprojects.service.mapper;

import com.example.simpleprojects.dto.SubjDto;
import com.example.simpleprojects.model.SubjTeach;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class SubjTeachMapper {

    @Mapping(target = "subjectTeachId",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)

    public abstract SubjTeach toEntity(SubjDto dto);

    public abstract SubjDto toDto(SubjTeach subjTeach);


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract void toUpdate(@MappingTarget SubjTeach subjTeach, SubjDto dto);
}
