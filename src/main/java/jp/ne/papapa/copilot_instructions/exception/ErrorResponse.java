package jp.ne.papapa.copilot_instructions.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * エラーレスポンスのモデルクラス
 * 例外発生時に返されるレスポンスのフォーマットを定義
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private String message;
    private String code;
    private LocalDateTime timestamp;
    private String path;
    private List<String> details;
    
    public static ErrorResponse of(String message, String code, String path) {
        return ErrorResponse.builder()
                .message(message)
                .code(code)
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
    }
    
    public static ErrorResponse of(String message, String code, String path, List<String> details) {
        return ErrorResponse.builder()
                .message(message)
                .code(code)
                .timestamp(LocalDateTime.now())
                .path(path)
                .details(details)
                .build();
    }
}
