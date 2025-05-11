package com.greedy.leaderboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileRequest {

    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    @Size(max = 10, message = "닉네임은 10자 이내로 입력해주세요.")
    private String nickname;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
    private String phone;
}

