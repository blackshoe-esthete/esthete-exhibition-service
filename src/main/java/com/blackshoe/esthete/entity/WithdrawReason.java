package com.blackshoe.esthete.entity;

import com.blackshoe.esthete.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "withdraw_reasons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithdrawReason extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdraw_reasons_id")
    private Long id;

    @Column(name = "reason", columnDefinition = "TEXT", nullable = false)
    private String reason;
}