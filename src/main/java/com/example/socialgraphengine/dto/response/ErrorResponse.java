package com.example.socialgraphengine.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Builder
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {


    @Builder.Default
    private  LocalDateTime timestamp = LocalDateTime.now();
    private  String message;
    private  String errorCode;
    private  int httpStatus;
    private  String path;
    @Builder.Default
    Map<String, String> extra = new HashMap<>();
}
