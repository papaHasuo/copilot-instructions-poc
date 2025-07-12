package jp.ne.papapa.copilot_instructions.service.impl;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザー検索サービスの実装
 * 検索条件に基づいてユーザー情報を取得するビジネスロジックを提供
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserSearchServiceImpl implements UserSearchService {

        private final UserRepository userRepository;

        @Override
        public Page<UserResponseDto> searchUsers(
                        String email,
                        String firstName,
                        String lastName,
                        User.UserStatus status,
                        Pageable pageable) {

                Page<User> users = userRepository.findUsersWithCriteria(
                                email, firstName, lastName, status, pageable);

                return users.map(UserResponseDto::fromEntity);
        }
}
