package com.example.simpleprojects.controller;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.StudentsDto;
import com.example.simpleprojects.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "std")
public class StudentController implements SimpleCrud<Integer, StudentsDto> {

    private final StudentService studentService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<StudentsDto> create(@RequestBody StudentsDto dto) {
        return this.studentService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<StudentsDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.studentService.get(entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<StudentsDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.studentService.delete(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<StudentsDto> update(@RequestBody StudentsDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.studentService.update(dto, entityId);
    }
}
