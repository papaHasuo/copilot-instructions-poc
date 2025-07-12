package jp.ne.papapa.copilot_instructions.exception;

/**
 * ユーザーが既に存在する場合にスローされる例外
 */
public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
