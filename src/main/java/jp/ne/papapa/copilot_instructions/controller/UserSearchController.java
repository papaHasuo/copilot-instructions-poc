package jp.ne.papapa.copilot_instructions.controller;

import jp.ne.papapa.copilot_instructions.common.Constants;
import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.entity.User;
import jp.ne.papapa.copilot_instructions.service.UserSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザー検索用のコントローラー
 * 様々な条件でユーザー情報を検索するエンドポイントを提供
 */
@RestController
@RequestMapping("/api/v1/users/search")
@RequiredArgsConstructor
@Slf4j
public class UserSearchController {
    
    private final UserSearchService userSearchService;
    
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> searchUsers(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) User.UserStatus status,
            @RequestParam(defaultValue = "" + Constants.DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = "" + Constants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDir) {
        
        
        Sort sort = sortDir.equalsIgnoreCase(Constants.SORT_DIRECTION_DESC) 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<UserResponseDto> users = userSearchService.searchUsers(
                email, firstName, lastName, status, pageable);
        
        return ResponseEntity.ok(users);
    }
}
