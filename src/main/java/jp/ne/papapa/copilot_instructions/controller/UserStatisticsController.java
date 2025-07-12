package jp.ne.papapa.copilot_instructions.controller;

import jp.ne.papapa.copilot_instructions.service.UserStatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ユーザー統計情報取得用のコントローラー
 * ユーザーに関する統計情報を取得するエンドポイントを提供
 */
@RestController
@RequestMapping("/api/v1/users/statistics")
@RequiredArgsConstructor
@Slf4j
public class UserStatisticsController {
    
    private final UserStatisticsService userStatisticsService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserStatistics() {
        Map<String, Object> statistics = userStatisticsService.getUserStatistics();
        return ResponseEntity.ok(statistics);
    }
}
