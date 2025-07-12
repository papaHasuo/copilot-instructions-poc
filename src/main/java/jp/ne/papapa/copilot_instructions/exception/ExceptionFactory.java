package jp.ne.papapa.copilot_instructions.exception;

import lombok.RequiredArgsConstructor;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * 例外を生成するファクトリクラス
 * 各種例外を生成するためのメソッドを提供
 */
@Component
@RequiredArgsConstructor
public class ExceptionFactory {
    
    private final MessageSource messageSource;
    
    public UserNotFoundException createUserNotFoundById(Long id) {
        String message = messageSource.getMessage("error.user.not.found.by.id", new Object[]{id}, Locale.getDefault());
        return new UserNotFoundException(message);
    }
    
    public UserNotFoundException createUserNotFoundByEmail(String email) {
        String message = messageSource.getMessage("error.user.not.found.by.email", new Object[]{email}, Locale.getDefault());
        return new UserNotFoundException(message);
    }
    
    public UserAlreadyExistsException createUserAlreadyExistsByEmail(String email) {
        String message = messageSource.getMessage("error.user.already.exists.by.email", new Object[]{email}, Locale.getDefault());
        return new UserAlreadyExistsException(message);
    }
}
