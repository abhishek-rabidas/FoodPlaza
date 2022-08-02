package com.foodplaza.DTO.Common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonApiResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public CommonApiResponse() {
        this.statusCode = 200;
        this.message = "OK";
    }

    public CommonApiResponse(T data) {
        this.statusCode = 200;
        this.message = "OK";
        this.data = data;
    }
}
