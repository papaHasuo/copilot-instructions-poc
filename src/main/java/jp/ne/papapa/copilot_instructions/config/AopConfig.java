package jp.ne.papapa.copilot_instructions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP設定クラス
 * AspectJの自動プロキシ機能を有効化
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
}
