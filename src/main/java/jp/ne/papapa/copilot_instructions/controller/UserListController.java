package jp.ne.papapa.copilot_instructions.controller;

import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.service.UserListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ユーザー一覧取得用のコントローラー
 * 全ユーザー情報を取得するエンドポイントを提供
 */
@RestController
@RequestMapping("/api/v1/users/list")
@RequiredArgsConstructor
@Slf4j
public class UserListController {
    
    private final UserListService userListService;
    
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userListService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
