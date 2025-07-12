package jp.ne.papapa.copilot_instructions.service;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSearchService {
    Page<UserResponseDto> searchUsers(
        String email, 
        String firstName, 
        String lastName, 
        User.UserStatus status, 
        Pageable pageable
    );
}
