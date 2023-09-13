package com.example.simpleprojects.service.mapper;

import com.example.simpleprojects.dto.MarksDto;
import com.example.simpleprojects.model.Marks;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public abstract class MarksMapper {
    @Mapping(target = "markId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Marks toEntity(MarksDto dto);


    public abstract MarksDto toDto(Marks marks);


    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdate(@MappingTarget Marks marks, MarksDto dto);
}
