package jp.ne.papapa.copilot_instructions.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * メソッド実行ログ出力用のAspect
 * サービス層とコントローラー層のメソッド開始・終了・例外発生時のログを自動出力
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    
    private final MessageSource messageSource;
    
    public LoggingAspect(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    /**
     * サービス層のメソッド実行をインターセプト
     */
    @Pointcut("within(jp.ne.papapa.copilot_instructions.service..*)")
    public void serviceLayer() {}
    
    /**
     * コントローラー層のメソッド実行をインターセプト
     */
    @Pointcut("within(jp.ne.papapa.copilot_instructions.controller..*)")
    public void controllerLayer() {}
    
    /**
     * リポジトリ層のメソッド実行をインターセプト
     */
    @Pointcut("within(jp.ne.papapa.copilot_instructions.repository..*)")
    public void repositoryLayer() {}
    
    /**
     * メソッド実行のログ出力（開始・終了・実行時間）
     */
    @Around("serviceLayer() || controllerLayer()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        // メソッド開始ログ
        String startMessage = messageSource.getMessage(
            "log.method.start", 
            new Object[]{className, methodName, Arrays.toString(args)}, 
            LocaleContextHolder.getLocale()
        );
        log.info(startMessage);
        
        // メソッド実行
        Object result = joinPoint.proceed();
        
        // メソッド正常終了ログ
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        String endMessage = messageSource.getMessage(
            "log.method.end", 
            new Object[]{className, methodName, executionTime}, 
            LocaleContextHolder.getLocale()
        );
        log.info(endMessage);
        
        return result;
    }
    
    /**
     * リポジトリメソッドのログ出力（シンプル版）
     */
    @Before("repositoryLayer()")
    public void logRepositoryMethodEntry(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        String message = messageSource.getMessage(
            "log.repository.call", 
            new Object[]{className, methodName, Arrays.toString(args)}, 
            LocaleContextHolder.getLocale()
        );
        log.debug(message);
    }
}
