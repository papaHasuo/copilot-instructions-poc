package jp.ne.papapa.copilot_instructions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.ne.papapa.copilot_instructions.dto.UserCreateRequestDto;
import jp.ne.papapa.copilot_instructions.dto.UserResponseDto;
import jp.ne.papapa.copilot_instructions.service.UserCreateService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserCreateController {

    private final UserCreateService userCreateService;

    @PostMapping("/api/v1/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto userDto) {
        UserResponseDto createdUser = userCreateService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // 作成成功時は201を返す
    }

}
