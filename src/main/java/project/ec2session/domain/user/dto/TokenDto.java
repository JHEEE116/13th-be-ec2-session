package project.ec2session.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenDto(

        @Schema(description = "접근 권한 토큰 - JWT", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJqaDA4MTUiLCJpc3MiOiJsaWtlbGlvbiIsImlhdCI6MTc0ODcyMTE4NCwiZXhwIjoxNzQ4NzIxNTQ0fQ.PfIMmpM5zT1gKVN-iEbIoCy5NmqVp2OGZsSoHmQr44I")
        String accessToken
) {
    public static TokenDto of(String accessToken) {
        return new TokenDto(accessToken);
    }
}
