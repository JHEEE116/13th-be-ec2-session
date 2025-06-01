package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import project.ec2session.domain.user.entity.User;

public class UserReq {

    public record SignUpDto(
            @Schema(description = "사용자 ID", example = "sjh0116")
            @NotBlank(message = "아이디는 필수 입력 값입니다.")
            String username,
            @Schema(description = "사용자 비밀번호", example = "Qaz159!@#")
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            String password,
            @Schema(description = "사용자 닉네임", example = "JHEEE116")
            @NotBlank(message = "닉네임은 필수 입력 값입니다.")
            String nickname
    ) {
        public User toEntity(String encodedPassword) {
            return User.builder()
                    .username(username)
                    .password(encodedPassword)
                    .nickname(nickname)
                    .build();
        }
    }

    public record SignInDto(
            @Schema(description = "사용자 ID", example = "sjh0116")
            @NotBlank(message = "아이디는 필수 입력 값입니다.")
            String username,
            @Schema(description = "사용자 비밀번호", example = "Qaz159!@#")
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            String password
    ) { }

    public record UpdateInfo(
            @Schema(description = "새로운 닉네임", example = "JHEEE000")
            @NotBlank(message = "닉네임은 필수 입력 값입니다.")
            String nickname
    ) { }
}
