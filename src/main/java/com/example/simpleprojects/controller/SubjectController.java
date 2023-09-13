package com.example.simpleprojects.controller;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.SubjectDto;
import com.example.simpleprojects.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "sub")
public class SubjectController implements SimpleCrud<Integer, SubjectDto> {

    private final SubjectService subjectService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<SubjectDto> create(@RequestBody SubjectDto dto) {
        return this.subjectService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<SubjectDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.subjectService.get(entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<SubjectDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.subjectService.delete(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<SubjectDto> update(@RequestBody SubjectDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.subjectService.update(dto, entityId);
    }
}
