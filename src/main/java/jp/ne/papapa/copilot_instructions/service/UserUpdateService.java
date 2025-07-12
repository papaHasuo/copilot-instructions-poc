package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.dto.UserUpdateRequestDto;

public interface UserUpdateService {
    UserResponseDto updateUser(Long id, UserUpdateRequestDto request);
}
