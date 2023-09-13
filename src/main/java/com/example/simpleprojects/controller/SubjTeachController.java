package com.example.simpleprojects.controller;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.SubjDto;
import com.example.simpleprojects.service.SubjTeachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "subj")
public class SubjTeachController implements SimpleCrud<Integer, SubjDto> {
    private final SubjTeachService subjTeachService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<SubjDto> create(@RequestBody SubjDto dto) {
        return this.subjTeachService.create(dto);
    }

    @GetMapping(value = "/get{id}")
    @Override
    public ResponseDto<SubjDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.subjTeachService.get(entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<SubjDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.subjTeachService.delete(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<SubjDto> update(@RequestBody SubjDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.subjTeachService.update(dto, entityId);
    }
}
