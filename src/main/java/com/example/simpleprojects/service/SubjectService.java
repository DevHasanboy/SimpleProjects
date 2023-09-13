package com.example.simpleprojects.service;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.SubjectDto;
import com.example.simpleprojects.model.Subjects;
import com.example.simpleprojects.repository.SubjectRepository;
import com.example.simpleprojects.service.mapper.SubjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService implements SimpleCrud<Integer, SubjectDto> {
    private final SubjectRepository subjectRepository;
    private final SubjectsMapper subjectsMapper;

    @Override
    public ResponseDto<SubjectDto> create(SubjectDto dto) {
        try {
            Subjects subjects = this.subjectsMapper.toEntity(dto);
            subjects.setCreatedAt(LocalDateTime.now());
            this.subjectRepository.save(subjects);
            return ResponseDto.<SubjectDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.subjectsMapper.toDto(subjects))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<SubjectDto>builder()
                    .code(-2)
                    .message("while is error saving ")
                    .build();


        }
    }

    @Override
    public ResponseDto<SubjectDto> get(Integer entityId) {
        Optional<Subjects> optional = this.subjectRepository.findBySubjectIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<SubjectDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        return ResponseDto.<SubjectDto>builder()
                .success(true)
                .message("ok")
                .data(this.subjectsMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<SubjectDto> delete(Integer entityId) {
        Optional<Subjects> optional = this.subjectRepository.findBySubjectIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<SubjectDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        Subjects subjects = optional.get();
        subjects.setDeletedAt(LocalDateTime.now());
        this.subjectRepository.save(subjects);
        return ResponseDto.<SubjectDto>builder()
                .success(true)
                .message("ok")
                .data(this.subjectsMapper.toDto(subjects))
                .build();
    }

    @Override
    public ResponseDto<SubjectDto> update(SubjectDto dto, Integer entityId) {
        try {
            Optional<Subjects> optional = this.subjectRepository.findBySubjectIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<SubjectDto>builder()
                        .code(-1)
                        .message("not found of id")
                        .build();
            }
            Subjects subjects = optional.get();
            this.subjectsMapper.toUpdate(subjects, dto);
            subjects.setUpdatedAt(LocalDateTime.now());
            this.subjectRepository.save(subjects);
            return ResponseDto.<SubjectDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.subjectsMapper.toDto(subjects))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<SubjectDto>builder()
                    .code(-2)
                    .message("while is error updating ")
                    .build();


        }
    }
}
