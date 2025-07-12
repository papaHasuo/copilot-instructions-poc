package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserCreateRequestDto;
import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;

public interface UserCreateService {
    UserResponseDto createUser(UserCreateRequestDto request);
}
