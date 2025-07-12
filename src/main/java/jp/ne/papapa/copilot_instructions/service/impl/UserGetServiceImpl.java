package jp.ne.papapa.copilot_instructions.service.impl;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.exception.ExceptionFactory;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザー取得サービスの実装
 * ユーザーIDでユーザー情報を取得するビジネスロジックを提供
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserGetServiceImpl implements UserGetService {
    
    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;
    
    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> exceptionFactory.createUserNotFoundById(id));
        
        return UserResponseDto.fromEntity(user);
    }
}
