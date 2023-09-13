package com.example.simpleprojects.testModul;

import com.example.simpleprojects.dto.GroupsDto;
import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.model.Groups;
import com.example.simpleprojects.repository.GroupsRepository;
import com.example.simpleprojects.service.GroupService;
import com.example.simpleprojects.service.mapper.GroupsMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


public class TestGroups {

    private GroupService groupService;
    private GroupsRepository groupsRepository;
    private GroupsMapper groupsMapper;


    @BeforeEach
    void init(){
        this.groupService= Mockito.mock(GroupService.class);
        this.groupsMapper=Mockito.mock(GroupsMapper.class);
        this.groupsRepository=Mockito.mock(GroupsRepository.class);

        this.groupService=new GroupService(groupsMapper,groupsRepository);

    }

    @Test
    void TestCreatePositive(){

        when(this.groupsMapper.toEntity(any()))
                .thenReturn(Groups.builder()
                        .groupId(1)
                        .name("hasanboy")
                        .build());

        when(this.groupsMapper.toDto(any()))
                .thenReturn(GroupsDto.builder()
                        .groupId(3)
                        .name("husanboy")
                        .build());


        ResponseDto<GroupsDto> response=this.groupService.create(any());
        Assertions.assertEquals(0,response.getCode());
        Assertions.assertEquals(3,response.getData().getGroupId());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());

      verify(this.groupsMapper,times(1)).toDto(any());
      verify(this.groupsMapper,times(1)).toEntity(any());



    }

    @Test
    void TestCreateException(){
        when(this.groupsMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<GroupsDto> response=this.groupService.create(any());

        Assertions.assertEquals(-1,response.getCode());

        Assertions.assertEquals(response.getMessage(),"while is saving create");
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());


    }

    @Test
    void TestGetPositive(){
        when(this.groupsRepository.findByGroupIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Groups.builder()
                                .name("hello")
                                .groupId(1)
                                .build()));


        when(this.groupsMapper.toDto(any()))
                .thenReturn(GroupsDto.builder()
                        .name("Tashkent")
                        .groupId(2)
                        .build());

        ResponseDto<GroupsDto> response=this.groupService.get(any());
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(response.getData().getGroupId(),2);
        Assertions.assertEquals(response.getData().getName(),"Tashkent");


        verify(this.groupsMapper,times(1)).toDto(any());
        verify(this.groupsRepository,times(1)).findByGroupIdAndDeletedAtIsNull(any());

    }
    @Test
    void TestGetNegative(){
        when(this.groupsRepository.findByGroupIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<GroupsDto>response=this.groupService.get(any());

        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());

    }

    @Test
    void TestUpdatePositive(){
        when(this.groupsRepository.findByGroupIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Groups.builder()
                                .groupId(2)
                                .name("what is your nane")
                                .build()));

        when(this.groupsMapper.toDto(any()))
                .thenReturn(GroupsDto.builder()
                        .name("Geeks for geeks")
                        .groupId(1)
                        .build());

        ResponseDto<GroupsDto> response=this.groupService.update(new GroupsDto(),any());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getName(),"Geeks for geeks");

        verify(this.groupsMapper,times(1)).toDto(any());
        verify(this.groupsRepository,times(1)).findByGroupIdAndDeletedAtIsNull(any());



    }

    @Test
    void TestUpdateException(){
        when(this.groupsRepository.findByGroupIdAndDeletedAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<GroupsDto> response=this.groupService.update(new GroupsDto(),any());

        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertEquals(response.getMessage(),"while is saving create");


    }
    @Test
    void TestDeletePositive(){
        when(groupsRepository.findByGroupIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Groups.builder()
                                .name("hi")
                                .groupId(3)
                                .build()));

        when(groupsMapper.toDto(any()))
                .thenReturn(GroupsDto.builder()
                        .name("good")
                        .groupId(4)
                        .build());


        ResponseDto<GroupsDto> response=this.groupService.delete(any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getName(),"good");
        Assertions.assertEquals(response.getData().getGroupId(),4);



        verify(this.groupsMapper,times(1)).toDto(any());
        verify(this.groupsRepository,times(1)).save(any());


    }

    @Test
    void TestDeleteNegative(){

        when(this.groupsRepository.findByGroupIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<GroupsDto> response=this.groupService.delete(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertEquals(response.getMessage(),"groups are empty !!");


    }

}
