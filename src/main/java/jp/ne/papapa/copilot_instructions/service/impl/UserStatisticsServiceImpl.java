package jp.ne.papapa.copilot_instructions.service.impl;

import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * ユーザー統計サービスの実装
 * ユーザーの統計情報を取得するビジネスロジックを提供
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserStatisticsServiceImpl implements UserStatisticsService {
    
    private final UserRepository userRepository;
    
    @Override
    public Map<String, Object> getUserStatistics() {
        
        long activeUsers = userRepository.countByStatus(User.UserStatus.ACTIVE);
        long inactiveUsers = userRepository.countByStatus(User.UserStatus.INACTIVE);
        long suspendedUsers = userRepository.countByStatus(User.UserStatus.SUSPENDED);
        long totalUsers = userRepository.count();
        
        return Map.of(
                "activeUsers", activeUsers,
                "inactiveUsers", inactiveUsers,
                "suspendedUsers", suspendedUsers,
                "totalUsers", totalUsers
        );
    }
}
