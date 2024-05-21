package com.blackshoe.esthete.exception;

import com.blackshoe.esthete.common.code.BaseErrorCode;
import com.blackshoe.esthete.common.dto.ErrorReasonDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExhibitionErrorResult implements BaseErrorCode {
    NOT_FOUND_TEMPORARY_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 임시저장 전시입니다."),
    NOT_FOUND_TEMPORARY_EXHIBITION_PHOTO(HttpStatus.NOT_FOUND, "404", "임시저장 전시에서 사진을 찾을 수 없습니다."),
    NOT_FOUND_TEMPORARY_EXHIBITION_TAG(HttpStatus.NOT_FOUND, "404", "임시저장 전시에서 태그를 찾을 수 없습니다."),
    NOT_FOUND_TAG(HttpStatus.NOT_FOUND, "404", "존재하지 않는 태그입니다."),
    NOT_FOUND_EXHIBITION(HttpStatus.NOT_FOUND, "404", "존재하지 않는 전시입니다."),
    NOT_FOUND_EXHIBITION_LOCATION(HttpStatus.NOT_FOUND, "404", "전시 위치가 존재하지 않습니다."),
    INVALID_EXHIBITION_PHOTO_IMG_SIZE(HttpStatus.UNPROCESSABLE_ENTITY, "422", "유효하지 않은 전시 사진입니다."),
    EXHIBITION_PHOTO_IMG_UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "400", "전시 사진 S3 업로드 실패했습니다."),
    NOT_FOUND_PHOTO_URL(HttpStatus.NOT_FOUND, "404", "존재하지 않는 사진 url입니다."),
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}