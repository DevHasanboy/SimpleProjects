package com.example.simpleprojects.service;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.SubjDto;
import com.example.simpleprojects.model.SubjTeach;
import com.example.simpleprojects.repository.SubjTeachRepository;
import com.example.simpleprojects.service.mapper.SubjTeachMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjTeachService implements SimpleCrud<Integer, SubjDto> {
    private final SubjTeachMapper subjTeachMapper;
    private final SubjTeachRepository subjTeachRepository;

    @Override
    public ResponseDto<SubjDto> create(SubjDto dto) {
        try {
            SubjTeach subjTeach = this.subjTeachMapper.toEntity(dto);
            subjTeach.setCreatedAt(LocalDateTime.now());
            this.subjTeachRepository.save(subjTeach);
            return ResponseDto.<SubjDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.subjTeachMapper.toDto(subjTeach))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<SubjDto>builder()
                    .code(-2)
                    .message("while is error saving")
                    .build();
        }
    }

    @Override
    public ResponseDto<SubjDto> get(Integer entityId) {
        Optional<SubjTeach> optional = this.subjTeachRepository.findBySubjectIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<SubjDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        return ResponseDto.<SubjDto>builder()
                .success(true)
                .message("ok")
                .data(this.subjTeachMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<SubjDto> delete(Integer entityId) {
        Optional<SubjTeach> optional = this.subjTeachRepository.findBySubjectIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<SubjDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        SubjTeach subjTeach = optional.get();
        subjTeach.setDeletedAt(LocalDateTime.now());
        this.subjTeachRepository.save(subjTeach);
        return ResponseDto.<SubjDto>builder()
                .success(true)
                .message("ok")
                .data(this.subjTeachMapper.toDto(subjTeach))
                .build();
    }

    @Override
    public ResponseDto<SubjDto> update(SubjDto dto, Integer entityId) {
        try {
            Optional<SubjTeach> optional = this.subjTeachRepository.findBySubjectIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<SubjDto>builder()
                        .code(-1)
                        .message("not found of id")
                        .build();
            }
            SubjTeach subjTeach = optional.get();
            this.subjTeachMapper.toUpdate(subjTeach, dto);
            subjTeach.setUpdatedAt(LocalDateTime.now());
            this.subjTeachRepository.save(subjTeach);

            return ResponseDto.<SubjDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.subjTeachMapper.toDto(subjTeach))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<SubjDto>builder()
                    .code(-2)
                    .message("while is error saving")
                    .build();
        }
    }
}
