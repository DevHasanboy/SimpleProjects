package com.example.simpleprojects.controller;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.TeachersDto;
import com.example.simpleprojects.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "teachs")
public class TeacherController implements SimpleCrud<Integer, TeachersDto> {

    private final TeacherService teacherService;

    @PostMapping(value = "'/create")
    @Override
    public ResponseDto<TeachersDto> create(@RequestBody TeachersDto dto) {
        return this.teacherService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<TeachersDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.teacherService.get(entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<TeachersDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.teacherService.delete(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<TeachersDto> update(@RequestBody TeachersDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.teacherService.update(dto, entityId);
    }
}
