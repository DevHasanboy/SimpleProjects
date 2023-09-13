package com.example.simpleprojects.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto <T> {
    private boolean success;
    private String  message;
    private  int code;
    private T data;

}
