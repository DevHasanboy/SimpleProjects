package com.example.simpleprojects.service;

import com.example.simpleprojects.dto.MarksDto;
import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.model.Marks;
import com.example.simpleprojects.repository.MarksRepository;
import com.example.simpleprojects.service.mapper.MarksMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkService implements SimpleCrud<Integer, MarksDto> {

    private final MarksMapper mapper;
    private  final MarksRepository marksRepository;
    @Override
    public ResponseDto<MarksDto> create(MarksDto dto) {
       try {
           Marks marks=this.mapper.toEntity(dto);
           marks.setCreatedAt(LocalDateTime.now());
           this.marksRepository.save(marks);
           return  ResponseDto.<MarksDto>builder()
                   .success(true)
                   .message("ok")
                   .data(this.mapper.toDto(marks))
                   .build();
       }
       catch (Exception e){
           return ResponseDto.<MarksDto>builder()
                   .code(-1)
                   .message("while is error saving")
                   .build();
       }
    }

    @Override
    public ResponseDto<MarksDto> get(Integer entityId) {
        Optional<Marks> optional=this.marksRepository.findByMarkIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<MarksDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();

        }
        return ResponseDto.<MarksDto>builder()
                .success(true)
                .message("ok")
                .data(this.mapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ResponseDto<MarksDto> delete(Integer entityId) {
        Optional<Marks> optional=this.marksRepository.findByMarkIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<MarksDto>builder()
                    .code(-1)
                    .message("not found of id")
                    .build();

        }
        Marks marks=optional.get();
        marks.setDeletedAt(LocalDateTime.now());
        this.marksRepository.save(marks);
        return ResponseDto.<MarksDto>builder()
                .success(true)
                .message("ok")
                .data(this.mapper.toDto(marks))
                .build();
    }

    @Override
    public ResponseDto<MarksDto> update(MarksDto dto, Integer entityId) {
       try {
           Optional<Marks> optional=this.marksRepository.findByMarkIdAndDeletedAtIsNull(entityId);
           if (optional.isEmpty()){
               return ResponseDto.<MarksDto>builder()
                       .code(-1)
                       .message("not found of id")
                       .build();

           }
           Marks marks=optional.get();
           this.mapper.toUpdate(marks,dto);
           marks.setUpdatedAt(LocalDateTime.now());
           this.marksRepository.save(marks);

           return ResponseDto.<MarksDto>builder()
                   .success(true)
                   .message("ok")
                   .data(this.mapper.toDto(marks))
                   .build();
       }
       catch (Exception e){
           return ResponseDto.<MarksDto>builder()
                   .code(-1)
                   .message("while is error saving")
                   .build();
       }
    }
}
