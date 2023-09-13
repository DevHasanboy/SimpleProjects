package com.example.simpleprojects.service;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.TeachersDto;
import com.example.simpleprojects.model.Teachers;
import com.example.simpleprojects.repository.TeachersRepository;
import com.example.simpleprojects.service.mapper.TeachersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements SimpleCrud<Integer, TeachersDto> {
    private final TeachersMapper teachersMapper;
    private final TeachersRepository teachersRepository;

    @Override
    public ResponseDto<TeachersDto> create(TeachersDto dto) {
        try {
            Teachers teachers = this.teachersMapper.toEntity(dto);
            teachers.setCreatedAt(LocalDateTime.now());
            this.teachersRepository.save(teachers);
            return ResponseDto.<TeachersDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.teachersMapper.toDto(teachers))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<TeachersDto>builder()
                    .code(-2)
                    .message("while is error saving")
                    .build();
        }
    }

    @Override
    public ResponseDto<TeachersDto> get(Integer entityId) {
        Optional<Teachers> optional = this.teachersRepository.findByTeacherIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<TeachersDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        return ResponseDto.<TeachersDto>builder()
                .success(true)
                .message("ok")
                .data(this.teachersMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<TeachersDto> delete(Integer entityId) {
        Optional<Teachers> optional = this.teachersRepository.findByTeacherIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<TeachersDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        Teachers teachers = optional.get();
        teachers.setDeletedAt(LocalDateTime.now());
        this.teachersRepository.save(teachers);
        return ResponseDto.<TeachersDto>builder()
                .success(true)
                .message("ok")
                .data(this.teachersMapper.toDto(teachers))
                .build();
    }

    @Override
    public ResponseDto<TeachersDto> update(TeachersDto dto, Integer entityId) {
        try {
            Optional<Teachers> optional = this.teachersRepository.findByTeacherIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<TeachersDto>builder()
                        .code(-1)
                        .message("not found of id")
                        .build();
            }
            Teachers teachers = optional.get();
            teachers.setUpdatedAt(LocalDateTime.now());
            this.teachersMapper.toUpdate(teachers, dto);
            this.teachersRepository.save(teachers);
            return ResponseDto.<TeachersDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.teachersMapper.toDto(teachers))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<TeachersDto>builder()
                    .code(-2)
                    .message("while is error updating ")
                    .build();
        }
    }
}
