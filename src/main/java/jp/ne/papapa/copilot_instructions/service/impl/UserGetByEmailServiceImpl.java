package jp.ne.papapa.copilot_instructions.service.impl;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.exception.ExceptionFactory;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserGetByEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザー取得サービスの実装
 * メールアドレスでユーザー情報を取得するビジネスロジックを提供
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserGetByEmailServiceImpl implements UserGetByEmailService {
    
    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;
    
    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> exceptionFactory.createUserNotFoundByEmail(email));
        
        return UserResponseDto.fromEntity(user);
    }
}
