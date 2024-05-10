package com.blackshoe.esthete.common.constant;

import com.blackshoe.esthete.common.code.BaseCode;
import com.blackshoe.esthete.common.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    // Global
    _OK(HttpStatus.OK, "200", "성공입니다."),
    _CREATED(HttpStatus.CREATED, "201", "성공적으로 생성되었습니다."),
    // MyGallery
    EDIT_USER_TAGS(HttpStatus.OK, "200", "선호 태그 수정에 성공했습니다."),
    EDIT_USER_PROFILE_IMG(HttpStatus.OK, "200", "프로필 사진 수정에 성공했습니다."),
    EDIT_USER_PROFILE_INFOS(HttpStatus.OK, "200", "프로필 정보 수정에 성공했습니다."),
    UPLOAD_EXHIBITION_PHOTO(HttpStatus.OK, "201", "전시 사진 추가에 성공했습니다."),
    UPLOAD_EXHIBITION(HttpStatus.CREATED, "201", "전시 제작에 성공했습니다."),
    // Exhibition
    SEARCH_ALL_EXHIBITION(HttpStatus.OK, "200", "전체 전시 검색에 성공했습니다."),
    SEARCH_EXHIBITION_BY_KEYWORD(HttpStatus.OK, "200", "전시 검색에 성공했습니다."),
    SEARCH_ALL_AUTHOR(HttpStatus.OK, "200", "전체 작가 검색에 성공했습니다."),
    SEARCH_AUTHOR_BY_KEYWORD(HttpStatus.OK, "200", "작가 검색에 성공했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .isSuccess(true)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}