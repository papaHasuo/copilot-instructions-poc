package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import java.util.List;

public interface UserListService {
    List<UserResponseDto> getAllUsers();
}
