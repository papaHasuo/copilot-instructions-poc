package jp.ne.papapa.copilot_instructions.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.ne.papapa.copilot_instructions.common.Constants;
import jp.ne.papapa.copilot_instructions.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー作成リクエストのDTOクラス
 * ユーザー登録時に使用されるリクエストデータを定義
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {
    
    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.invalid}")
    private String email;
    
    @NotBlank(message = "{validation.first.name.required}")
    @Size(max = Constants.FIRST_NAME_MAX_LENGTH, message = "{validation.first.name.size}")
    private String firstName;
    
    @NotBlank(message = "{validation.last.name.required}")
    @Size(max = Constants.LAST_NAME_MAX_LENGTH, message = "{validation.last.name.size}")
    private String lastName;
    
    @Size(max = Constants.PHONE_NUMBER_MAX_LENGTH, message = "{validation.phone.number.size}")
    private String phoneNumber;
    
    public User toEntity() {
        return User.builder()
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .status(User.UserStatus.ACTIVE)
                .build();
    }
}
