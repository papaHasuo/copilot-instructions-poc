package jp.ne.papapa.copilot_instructions.controller;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.service.UserGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザー取得用のコントローラー
 * ユーザー情報をIDで取得するエンドポイントを提供
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserGetController {
    
    private final UserGetService userGetService;
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userGetService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
