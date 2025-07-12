package jp.ne.papapa.copilot_instructions.config;

import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 初期データをロードするためのコンポーネント
 * アプリケーション起動時にユーザーデータをロード
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            log.info("Loading initial user data...");
            
            User user1 = User.builder()
                    .email("john.doe@example.com")
                    .firstName("John")
                    .lastName("Doe")
                    .phoneNumber("090-1234-5678")
                    .status(User.UserStatus.ACTIVE)
                    .build();
            
            User user2 = User.builder()
                    .email("jane.smith@example.com")
                    .firstName("Jane")
                    .lastName("Smith")
                    .phoneNumber("080-9876-5432")
                    .status(User.UserStatus.ACTIVE)
                    .build();
            
            User user3 = User.builder()
                    .email("bob.johnson@example.com")
                    .firstName("Bob")
                    .lastName("Johnson")
                    .phoneNumber("070-1111-2222")
                    .status(User.UserStatus.INACTIVE)
                    .build();
            
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            
            log.info("Initial user data loaded successfully");
        }
    }
}
