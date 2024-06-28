package com.blackshoe.esthete.entity;


import com.blackshoe.esthete.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "delete_reasons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteReason extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delete_reason_id")
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private DeleteReasonValue deleteReasonValue;

    @Builder
    public DeleteReason(String email, DeleteReasonValue deleteReasonValue){
        this.email = email;
        this.deleteReasonValue = deleteReasonValue;
    }
}
