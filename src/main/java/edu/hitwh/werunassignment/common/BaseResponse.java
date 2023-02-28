package edu.hitwh.werunassignment.common;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int code;
    private T data;
    private String message;
    private String description;

    BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    BaseResponse(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.data = data;
        this.message = errorCode.getMessage();
        this.description = description;
    }

}
