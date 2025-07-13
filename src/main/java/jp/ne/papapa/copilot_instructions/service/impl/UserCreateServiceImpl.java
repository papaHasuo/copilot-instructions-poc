package jp.ne.papapa.copilot_instructions.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.ne.papapa.copilot_instructions.dto.UserCreateRequestDto;
import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import jp.ne.papapa.copilot_instructions.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserCreateServiceImpl implements UserCreateService {
    
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto request) {
        log.info("Creating user with email: {}", request.getEmail());
        
        // ユーザーの作成処理
        try{
            UserResponseDto createdUser = UserResponseDto.fromEntity(userRepository.save(request.toEntity()));
            return createdUser;
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw e;
        }
    }

}
