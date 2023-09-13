package com.example.simpleprojects.testModul;

import com.example.simpleprojects.dto.MarksDto;
import com.example.simpleprojects.dto.ResponseDto;
import com.example.simpleprojects.model.Marks;
import com.example.simpleprojects.repository.MarksRepository;
import com.example.simpleprojects.service.MarkService;
import com.example.simpleprojects.service.mapper.MarksMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


public class TestMarks {

    private MarksMapper marksMapper;
    private MarkService markService;
    private MarksRepository marksRepository;


    @BeforeEach
    void init(){
        this.markService= Mockito.mock(MarkService.class);
        this.marksMapper=Mockito.mock(MarksMapper.class);
        this.marksRepository=Mockito.mock(MarksRepository.class);

        this.markService=new MarkService(marksMapper,marksRepository);

    }

    @Test
    void TestCreatePositive(){

        when(this.marksMapper.toEntity(any()))
                .thenReturn(Marks.builder()
                        .markId(1)
                        .studentId(1)
                        .build());

        when(this.marksMapper.toDto(any()))
                .thenReturn(MarksDto.builder()
                        .markId(3)
                        .subjectId(3)
                        .build());


        ResponseDto<MarksDto> response=this.markService.create(any());
        Assertions.assertEquals(0,response.getCode());
        Assertions.assertEquals(3,response.getData().getMarkId());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getData());

        verify(this.marksMapper,times(1)).toDto(any());
        verify(this.marksMapper,times(1)).toEntity(any());



    }

    @Test
    void TestCreateException(){
        when(this.marksMapper.toEntity(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<MarksDto> response=this.markService.create(any());

        Assertions.assertEquals(-1,response.getCode());

       Assertions.assertEquals(response.getMessage(),"while is error saving");
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());


    }

   @Test
    void TestGetPositive(){
        when(this.marksRepository.findByMarkIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Marks.builder()
                        .subjectId(3)
                        .markId(1)
                        .build()));


        when(this.marksMapper.toDto(any()))
                .thenReturn(MarksDto.builder()
                        .subjectId(2)
                        .markId(2)
                        .build());

        ResponseDto<MarksDto> response=this.markService.get(any());
        Assertions.assertNotNull(response.getData());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(response.getData().getMarkId(),2);
        Assertions.assertEquals(response.getData().getSubjectId(),2);


        verify(this.marksMapper,times(1)).toDto(any());
        verify(this.marksRepository,times(1)).findByMarkIdAndDeletedAtIsNull(any());

    }
    @Test
    void TestGetNegative(){
        when(this.marksRepository.findByMarkIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());


        ResponseDto<MarksDto>response=this.markService.get(any());

        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getData());

    }

    @Test
    void TestUpdatePositive(){
        when(this.marksRepository.findByMarkIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(Marks.builder()
                        .markId(2)
                        .subjectId(2)
                        .build()));

        when(this.marksMapper.toDto(any()))
                .thenReturn(MarksDto.builder()
                        .subjectId(3)
                        .markId(1)
                        .build());

        ResponseDto<MarksDto> response=this.markService.update(new MarksDto(),any());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getSubjectId(),3);
        Assertions.assertEquals(response.getData().getMarkId(),1);

        verify(this.marksMapper,times(1)).toDto(any());
        verify(this.marksRepository,times(1)).findByMarkIdAndDeletedAtIsNull(any());



    }

   @Test
    void TestUpdateException(){
        when(this.marksRepository.findByMarkIdAndDeletedAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<MarksDto> response=this.markService.update(new MarksDto(),any());

        Assertions.assertEquals(response.getCode(),-1);
       Assertions.assertEquals(response.getMessage(),"while is error saving");


    }
   @Test
    void TestDeletePositive(){
        when(marksRepository.findByMarkIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(Marks.builder()
                        .subjectId(2)
                        .markId(3)
                        .build()));

        when(marksMapper.toDto(any()))
                .thenReturn(MarksDto.builder()
                        .subjectId(4)
                        .markId(4)
                        .build());


        ResponseDto<MarksDto> response=this.markService.delete(any());
        Assertions.assertEquals(response.getCode(),0);
        Assertions.assertEquals(response.getData().getSubjectId(),4);
        Assertions.assertEquals(response.getData().getMarkId(),4);



        verify(this.marksMapper,times(1)).toDto(any());
        verify(this.marksRepository,times(1)).save(any());


    }

    @Test
    void TestDeleteNegative(){

        when(this.marksRepository.findByMarkIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<MarksDto> response=this.markService.delete(any());
        Assertions.assertEquals(response.getCode(),-1);
        Assertions.assertEquals(response.getMessage(),"not found of id");


    }

}
