package jp.ne.papapa.copilot_instructions.service.impl;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ne.papapa.copilot_instructions.dto.UserCreateRequestDto;
import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.exception.ExceptionFactory;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー登録サービスの実装
 * 新規ユーザー登録のビジネスロジックを提供
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto request) {
        
        // ユーザーの存在確認
        if (userRepository.existsByEmail(request.getEmail())) {
            throw exceptionFactory.createUserAlreadyExistsByEmail(request.getEmail());
        }

        User user = request.toEntity();
        User savedUser = userRepository.save(user);

        return UserResponseDto.fromEntity(savedUser);
    }
    
}
