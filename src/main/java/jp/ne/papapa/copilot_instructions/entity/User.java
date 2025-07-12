package jp.ne.papapa.copilot_instructions.entity;

import jakarta.persistence.*;
import jp.ne.papapa.copilot_instructions.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * ユーザーエンティティクラス
 * ユーザー情報を管理するためのエンティティ
 */
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = Constants.EMAIL_MAX_LENGTH)
    private String email;

    @Column(nullable = false, length = Constants.FIRST_NAME_MAX_LENGTH)
    private String firstName;

    @Column(nullable = false, length = Constants.LAST_NAME_MAX_LENGTH)
    private String lastName;

    @Column(length = Constants.PHONE_NUMBER_MAX_LENGTH)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}
