package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.code.BaseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // Token Error
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse<BaseErrorCode>> handleTokenException(TokenException e) {
        TokenErrorResult errorResult = e.getTokenErrorResult();
        return ApiResponse.onFailure(errorResult);
    }
    // User Error
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<BaseErrorCode>> handleUserException(UserException e) {
        UserErrorResult errorResult = e.getUserErrorResult();
        return ApiResponse.onFailure(errorResult);
    }
    // Tag Error
    @ExceptionHandler(TagException.class)
    public ResponseEntity<ApiResponse<BaseErrorCode>> handleUserException(TagException e) {
        TagErrorResult errorResult = e.getTagErrorResult();
        return ApiResponse.onFailure(errorResult);
    }
    // S3 Error
    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<ApiResponse<BaseErrorCode>> handleS3Exception(S3Exception e) {
        S3ErrorResult errorResult = e.getS3ErrorResult();
        return ApiResponse.onFailure(errorResult);
    }
    // MyGallery Error
    @ExceptionHandler(MyGalleryException.class)
    public ResponseEntity<ApiResponse<BaseErrorCode>> handleMyGalleryException(MyGalleryException e) {
        MyGalleryErrorResult errorResult = e.getMyGalleryErrorResult();
        return ApiResponse.onFailure(errorResult);
    }
}