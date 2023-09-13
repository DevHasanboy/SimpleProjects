package com.example.simpleprojects.controller;

import com.example.simpleprojects.dto.MarksDto;
import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.service.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "mark")
public class MarkController implements SimpleCrud<Integer, MarksDto> {

    private final MarkService markService;

    @PostMapping(value = "/create")
    @Override
    public ResponseDto<MarksDto> create(@RequestBody MarksDto dto) {
        return this.markService.create(dto);
    }

    @GetMapping(value = "/get{id}")
    @Override
    public ResponseDto<MarksDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.markService.get(entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<MarksDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.markService.delete(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<MarksDto> update(@RequestBody MarksDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.markService.update(dto, entityId);
    }
}
