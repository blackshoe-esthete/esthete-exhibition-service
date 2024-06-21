package com.blackshoe.esthete.exception;


import com.blackshoe.esthete.entity.ResponseDto;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

//    @ExceptionHandler(AdminException.class)
//    public ResponseEntity<ResponseDto> handleAdminException(AdminException e) {
//
//        log.error("AdminException : " + e.getMessage());
//
//        final AdminErrorResult errorResult = e.getAdminErrorResult();
//
//        final ResponseDto responseDto = ResponseDto.error()
//                .error(errorResult.getMessage())
//                .build();
//
//        return ResponseEntity.status(errorResult.getHttpStatus()).body(responseDto);
//    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseDto> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {

        log.error("MaxUploadSizeExceededException", e);

        final ResponseDto responseDto = ResponseDto.error()
                .error("파일 크기가 50MB보다 큽니다.")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ResponseDto> authException(AuthException e){

        log.error("AuthException : " + e);

        final ResponseDto responseDto = ResponseDto.error()
                .error(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception e) {

        log.error("Exception" + String.valueOf(e));

        if(e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")){
            final ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
        }

        final ResponseDto responseDto = ResponseDto.builder()
                .error(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }

}


