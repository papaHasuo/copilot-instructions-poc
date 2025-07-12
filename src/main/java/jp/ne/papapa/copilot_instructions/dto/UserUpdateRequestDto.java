package jp.ne.papapa.copilot_instructions.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jp.ne.papapa.copilot_instructions.common.Constants;
import jp.ne.papapa.copilot_instructions.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー更新リクエストのDTOクラス
 * ユーザー情報更新時に使用されるリクエストデータを定義
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    
    @Email(message = "{validation.email.invalid}")
    private String email;
    
    @Size(max = Constants.FIRST_NAME_MAX_LENGTH, message = "{validation.first.name.size}")
    private String firstName;
    
    @Size(max = Constants.LAST_NAME_MAX_LENGTH, message = "{validation.last.name.size}")
    private String lastName;
    
    @Size(max = Constants.PHONE_NUMBER_MAX_LENGTH, message = "{validation.phone.number.size}")
    private String phoneNumber;
    
    private User.UserStatus status;
}
