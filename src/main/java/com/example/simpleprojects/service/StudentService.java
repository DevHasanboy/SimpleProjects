package com.example.simpleprojects.service;

import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.dto.StudentsDto;
import com.example.simpleprojects.model.Students;
import com.example.simpleprojects.repository.StudentsRepository;
import com.example.simpleprojects.service.mapper.StudentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements SimpleCrud<Integer, StudentsDto> {

    private final StudentsMapper studentsMapper;
    private final StudentsRepository studentsRepository;

    @Override
    public ResponseDto<StudentsDto> create(StudentsDto dto) {
      try {
          Students students=this.studentsMapper.toEntity(dto);
          students.setCreatedAt(LocalDateTime.now());
          this.studentsRepository.save(students);
          return ResponseDto.<StudentsDto>builder()
                  .success(true)
                  .message("ok")
                  .data(this.studentsMapper.toDto(students))
                  .build();
      }
      catch (Exception e){

          return ResponseDto.<StudentsDto>builder()
                  .code(-2)
                  .message("while is error saving")
                  .build();
      }
    }

    @Override
    public ResponseDto<StudentsDto> get(Integer entityId) {
        Optional<Students> optional=this.studentsRepository.findByGroupIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<StudentsDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        return ResponseDto.<StudentsDto>builder()
                .success(true)
                .message("ok")
                .data(this.studentsMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<StudentsDto> delete(Integer entityId) {
        Optional<Students> optional=this.studentsRepository.findByGroupIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<StudentsDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();
        }
        Students students=optional.get();
        students.setDeletedAt(LocalDateTime.now());
        this.studentsRepository.save(students);
        return ResponseDto.<StudentsDto>builder()
                .success(true)
                .message("ok")
                .data(this.studentsMapper.toDto(students))
                .build();
    }

    @Override
    public ResponseDto<StudentsDto> update(StudentsDto dto, Integer entityId) {
      try {
          Optional<Students> optional=this.studentsRepository.findByGroupIdAndDeletedAtIsNull(entityId);
          if (optional.isEmpty()){
              return ResponseDto.<StudentsDto>builder()
                      .code(-1)
                      .message("not found of id")
                      .build();
          }
          Students students=optional.get();
          this.studentsMapper.toUpdate(students,dto);
          students.setUpdatedAt(LocalDateTime.now());
          this.studentsRepository.save(students);
          return ResponseDto.<StudentsDto>builder()
                  .success(true)
                  .message("ok")
                  .data(this.studentsMapper.toDto(students))
                  .build();
      }
      catch (Exception e){

          return ResponseDto.<StudentsDto>builder()
                  .code(-2)
                  .message("while is error saving")
                  .build();
      }
    }
}
