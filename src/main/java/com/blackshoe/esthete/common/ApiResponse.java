package com.blackshoe.esthete.common;

import com.blackshoe.esthete.common.code.BaseCode;
import com.blackshoe.esthete.common.constant.SuccessStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T payload;

    //data만 들어가는 응답 포맷
    public static <T> ApiResponse<T> onSuccess(T data) {
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), data);
    }

    public static <T> ApiResponse<T> onSuccess(BaseCode code, T data) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(),
                code.getReasonHttpStatus().getMessage(), data);
    }

    public static <T> ApiResponse<T> onSuccess(HttpStatus data) {
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), null);
    }

    //message까지 같이 넣을 수 있는 응답 포맷
    public static <T> ApiResponse<T> onSuccess(String message, T data) {
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), message, data);
    }

    //아래부터는 POST시 사용❗️
    //data만 들어가는 응답 포맷
    public static <T> ApiResponse<T> onSuccessCreated(T data) {
        return new ApiResponse<>(true, SuccessStatus._CREATED.getCode(), SuccessStatus._OK.getMessage(), data);
    }

    public static <T> ApiResponse<T> onSuccessCreated(BaseCode code, T data) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(),
                code.getReasonHttpStatus().getMessage(), data);
    }

    public static <T> ApiResponse<T> onSuccessCreated(HttpStatus data) {
        return new ApiResponse<>(true, SuccessStatus._CREATED.getCode(), SuccessStatus._OK.getMessage(), null);
    }

    //message까지 같이 넣을 수 있는 응답 포맷
    public static <T> ApiResponse<T> onSuccessCreated(String message, T data) {
        return new ApiResponse<>(true, SuccessStatus._CREATED.getCode(), message, data);
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}
