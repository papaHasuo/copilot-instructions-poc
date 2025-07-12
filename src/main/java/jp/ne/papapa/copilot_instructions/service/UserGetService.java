package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;

public interface UserGetService {
    UserResponseDto getUserById(Long id);
}
