package jp.ne.papapa.copilot_instructions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.ne.papapa.copilot_instructions.dto.UserCreateRequestDto;
import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
public class UserCreateController {
    
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(UserCreateController.class);

    @PostMapping("/api/v1/users")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateRequestDto userDto) {
        
        // 開始ログの出力
        log.info("POST /api/v1/users - Creating new user: {}", userDto);

        // ユーザーの作成処理
        try{
            UserResponseDto createdUser = UserResponseDto.fromEntity(userRepository.save(userDto.toEntity()));
            return ResponseEntity.ok(createdUser); // 作成したユーザー情報を返す
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return ResponseEntity.status(500).body(null); // エラー時は500を返す
        }
    }
    
}
