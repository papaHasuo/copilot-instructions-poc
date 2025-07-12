package jp.ne.papapa.copilot_instructions.controller;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.service.UserGetByStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ユーザー取得用のコントローラー
 * ステータスでユーザー情報を取得するエンドポイントを提供
 */
@RestController
@RequestMapping("/api/v1/users/status")
@RequiredArgsConstructor
@Slf4j
public class UserGetByStatusController {
    
    private final UserGetByStatusService userGetByStatusService;
    
    @GetMapping("/{status}")
    public ResponseEntity<List<UserResponseDto>> getUsersByStatus(@PathVariable User.UserStatus status) {
        List<UserResponseDto> users = userGetByStatusService.getUsersByStatus(status);
        return ResponseEntity.ok(users);
    }
}
