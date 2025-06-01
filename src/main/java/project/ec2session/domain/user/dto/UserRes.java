package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import project.ec2session.domain.user.entity.User;

public record UserRes(
        @Schema(description = "사용자 ID" , example = "1")
         Long userId,
         @Schema(description = "사용자 로그인 ID(이메일 또는 사용자 이름)" , example = "sjh0116")
         String username,
         @Schema(description = "사용자 이름", example = "JHEEE00116")
         String nickname
) {
    public static UserRes from(User user) {
        return new UserRes(user.getId(), user.getUsername(), user.getNickname());
    }
}
