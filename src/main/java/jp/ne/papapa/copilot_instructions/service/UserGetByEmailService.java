package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;

public interface UserGetByEmailService {
    UserResponseDto getUserByEmail(String email);
}
