package jp.ne.papapa.copilot_instructions.controller;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.service.UserGetByEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザー取得用のコントローラー
 * メールアドレスでユーザー情報を取得するエンドポイントを提供
 */
@RestController
@RequestMapping("/api/v1/users/email")
@RequiredArgsConstructor
@Slf4j
public class UserGetByEmailController {
    
    private final UserGetByEmailService userGetByEmailService;
    
    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        UserResponseDto user = userGetByEmailService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
