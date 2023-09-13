package com.example.simpleprojects.service;

import com.example.simpleprojects.dto.GroupsDto;
import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.dto.SimpleCrud;
import com.example.simpleprojects.model.Groups;
import com.example.simpleprojects.repository.GroupsRepository;
import com.example.simpleprojects.service.mapper.GroupsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements SimpleCrud<Integer, GroupsDto> {

    private final GroupsMapper groupsMapper;
    private final GroupsRepository groupsRepository;



    @Override
    public ResponseDto<GroupsDto> create(GroupsDto dto) {
        try {
            Groups groups=this.groupsMapper.toEntity(dto);
            groups.setCreatedAt(LocalDateTime.now());
            this.groupsRepository.save(groups);
            return ResponseDto.<GroupsDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.groupsMapper.toDto(groups))
                    .build();
        }
        catch (Exception e){

        return ResponseDto.<GroupsDto>builder()
                .code(-1)
                .message("while is saving create")
                .build();
        }
    }

    @Override
    public ResponseDto<GroupsDto> get(Integer entityId) {
        Optional<Groups> optional=this.groupsRepository.findByGroupIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("groups are empty !!")
                    .build();


        }

            Groups groups=optional.get();
            return ResponseDto.<GroupsDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.groupsMapper.toDto(groups))
                    .build();


    }

    @Override
    public ResponseDto<GroupsDto> delete(Integer entityId) {
        Optional<Groups> optional=this.groupsRepository.findByGroupIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()){
            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("groups are empty !!")
                    .build();


        }
        Groups groups=optional.get();
        groups.setDeletedAt(LocalDateTime.now());
        this.groupsRepository.save(groups);
        return ResponseDto.<GroupsDto>builder()
                .success(true)
                .message("ok")
                .data(this.groupsMapper.toDto(groups))
                .build();

    }

    @Override
    public ResponseDto<GroupsDto> update(GroupsDto dto, Integer entityId) {
        try {
            Optional<Groups> optional=this.groupsRepository.findByGroupIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()){
                return ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("groups are empty !!")
                        .build();


            }
            Groups groups=optional.get();
            groups.setUpdatedAt(LocalDateTime.now());
            this.groupsMapper.toUpdate(groups,dto);
            this.groupsRepository.save(groups);

            return ResponseDto.<GroupsDto>builder()
                    .success(true)
                    .message("ok")
                    .data(this.groupsMapper.toDto(groups))
                    .build();
        }
        catch (Exception e){
            return ResponseDto.<GroupsDto>builder()
                    .code(-1)
                    .message("while is saving create")
                    .build();
        }

    }
}
