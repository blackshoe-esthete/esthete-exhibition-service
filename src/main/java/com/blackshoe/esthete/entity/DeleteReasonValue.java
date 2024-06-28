package com.blackshoe.esthete.entity;

public enum DeleteReasonValue {
    NOT_A_TARGET_USER("서비스 이용 대상이 아님"),
    TOO_COMPLEX("서비스 이용이 복잡하고 어려움"),
    SERVICE_ERROR("서비스 장애와 오류 때문에"),
    NO_INTENTION_AFTER_REFUND("환불 후 이용 의사가 없어서"),
    REJOINING_AFTER_DELETION("탈퇴 후 신규 가입하기 위함"),
    OTHER("기타");

    private final String reason;

    DeleteReasonValue(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
