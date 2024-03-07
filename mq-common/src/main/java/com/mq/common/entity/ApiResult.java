package com.mq.common.entity;

import lombok.Data;

/**
 * @description:
 * @author: damon.liu
 * @date: 2024/2/2 16:27
 */
@Data
public class ApiResult<T> {

    private String code;

    private String message;

    private T data;

    public ApiResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult() {
    }

    public static ApiResult success() {
        return new ApiResult("200", "success");
    }

    public static ApiResult success(Object data) {
        return new ApiResult("200", "success", data);
    }

    public static ApiResult error(String code, String message) {
        return new ApiResult(code, message);
    }

    public static ApiResult error(String message) {
        return new ApiResult("500", message);
    }
}
