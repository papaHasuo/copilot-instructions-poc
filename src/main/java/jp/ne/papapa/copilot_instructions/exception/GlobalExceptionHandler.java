package jp.ne.papapa.copilot_instructions.exception;

import jp.ne.papapa.copilot_instructions.common.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * グローバルな例外ハンドラー
 * 各種例外をキャッチして適切なレスポンスを返す
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    
    private final MessageSource messageSource;
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        log.error(messageSource.getMessage("log.error.user.not.found", new Object[]{ex.getMessage()}, "ユーザーが見つかりません: {0}", request.getLocale()));
        
        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getMessage(),
                Constants.ERROR_CODE_USER_NOT_FOUND,
                request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException ex, WebRequest request) {
        log.error(messageSource.getMessage("log.error.user.already.exists", new Object[]{ex.getMessage()}, "ユーザーは既に存在します: {0}", request.getLocale()));
        
        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getMessage(),
                Constants.ERROR_CODE_USER_ALREADY_EXISTS,
                request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {
        log.error(messageSource.getMessage("log.error.validation", new Object[]{ex.getMessage()}, "バリデーションエラー: {0}", request.getLocale()));
        
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .collect(Collectors.toList());
        
        ErrorResponse errorResponse = ErrorResponse.of(
                messageSource.getMessage("error.validation.failed", null, "入力値に誤りがあります", request.getLocale()),
                Constants.ERROR_CODE_VALIDATION_ERROR,
                request.getDescription(false).replace("uri=", ""),
                errors
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.error(messageSource.getMessage("log.error.illegal.argument", new Object[]{ex.getMessage()}, "不正な引数: {0}", request.getLocale()));
        
        ErrorResponse errorResponse = ErrorResponse.of(
                ex.getMessage(),
                Constants.ERROR_CODE_ILLEGAL_ARGUMENT,
                request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        log.error(messageSource.getMessage("log.error.unexpected", null, request.getLocale()));
        
        ErrorResponse errorResponse = ErrorResponse.of(
                messageSource.getMessage("error.internal_server_error", null, "内部サーバーエラーが発生しました", request.getLocale()),
                Constants.ERROR_CODE_INTERNAL_SERVER_ERROR,
                request.getDescription(false).replace("uri=", "")
        );
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
