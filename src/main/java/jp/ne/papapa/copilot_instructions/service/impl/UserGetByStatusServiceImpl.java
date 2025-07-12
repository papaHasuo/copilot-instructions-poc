package jp.ne.papapa.copilot_instructions.service.impl;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserGetByStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ユーザー取得サービスの実装
 * ステータスでユーザー情報を取得するビジネスロジックを提供
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserGetByStatusServiceImpl implements UserGetByStatusService {
    
    private final UserRepository userRepository;
    
    @Override
    public List<UserResponseDto> getUsersByStatus(User.UserStatus status) {
        return userRepository.findByStatus(status).stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
