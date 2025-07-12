package jp.ne.papapa.copilot_instructions.common;

/**
 * 定数を定義するクラス
 * アプリケーション全体で使用される定数を管理
 */
public final class Constants {
    
    private Constants() {
    }
    
    // Database Column Lengths
    public static final int EMAIL_MAX_LENGTH = 100;
    public static final int FIRST_NAME_MAX_LENGTH = 50;
    public static final int LAST_NAME_MAX_LENGTH = 50;
    public static final int PHONE_NUMBER_MAX_LENGTH = 20;
    
    // Pagination Defaults
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";
    public static final String SORT_DIRECTION_DESC = "desc";
    
    // API Error Codes
    public static final String ERROR_CODE_USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String ERROR_CODE_USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
    public static final String ERROR_CODE_VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String ERROR_CODE_ILLEGAL_ARGUMENT = "ILLEGAL_ARGUMENT";
    public static final String ERROR_CODE_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
}
