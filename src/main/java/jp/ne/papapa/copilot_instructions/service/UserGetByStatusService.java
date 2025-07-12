package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import java.util.List;

public interface UserGetByStatusService {
    List<UserResponseDto> getUsersByStatus(User.UserStatus status);
}
