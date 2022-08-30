package com.example.demo.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseHelper {

    public static ResponseEntity<Object> GenerateResponse(Boolean success, String message, HttpStatus status) {
        return new ResponseEntity<Object>(new ApiResponse<Object>(success, message),status);
    }

    public static ResponseEntity<Object> GenerateResponse(Boolean success, String message, String type, Object data, HttpStatus status) {
        return new ResponseEntity<Object>(new ApiResponse<Object>(success, message,type , data), status);
    }

}
