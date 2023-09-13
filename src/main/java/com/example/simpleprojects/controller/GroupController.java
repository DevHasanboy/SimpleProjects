package com.example.simpleprojects.controller;

import com.example.simpleprojects.dto.GroupsDto;
import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "gro")
public class GroupController implements SimpleCrud<Integer, GroupsDto> {

    private final GroupService groupService;


    @PostMapping(value = "/create")
    @Override
    public ResponseDto<GroupsDto> create(@RequestBody @Valid GroupsDto dto) {
        return this.groupService.create(dto);
    }

    @GetMapping(value = "/get/{id}")
    @Override
    public ResponseDto<GroupsDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.groupService.get(entityId);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Override
    public ResponseDto<GroupsDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.groupService.delete(entityId);
    }

    @PutMapping(value = "/update/{id}")
    @Override
    public ResponseDto<GroupsDto> update(@RequestBody GroupsDto dto, @PathVariable(value = "id") Integer entityId) {
        return this.groupService.update(dto, entityId);
    }
}
