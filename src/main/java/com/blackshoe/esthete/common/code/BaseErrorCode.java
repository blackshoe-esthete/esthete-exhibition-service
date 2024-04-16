package com.blackshoe.esthete.common.code;


import com.blackshoe.esthete.common.dto.ErrorReasonDto;

public interface BaseErrorCode {
    public ErrorReasonDto getReason();

    public ErrorReasonDto getReasonHttpStatus();
}
