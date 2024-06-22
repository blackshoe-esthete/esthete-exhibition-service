package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.ApiResponse;
import com.blackshoe.esthete.common.code.BaseErrorCode;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // File Size
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("MaxUploadSizeExceededException", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("파일 크기가 50MB보다 큽니다.");
    }
    // Auth
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuthException(AuthException e) {
        log.error("AuthException", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    // Header
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> handleMissingHeaderException(MissingRequestHeaderException e) {
        String errorMessage = "Required header '" + e.getHeaderName() + "' is missing";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }
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
    // Exhibition Error
    @ExceptionHandler(ExhibitionException.class)
    public ResponseEntity<ApiResponse<BaseErrorCode>> handleExhibitionException(ExhibitionException e) {
        ExhibitionErrorResult errorResult = e.getExhibitionErrorResult();
        return ApiResponse.onFailure(errorResult);
    }
    // ETC
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("Exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}