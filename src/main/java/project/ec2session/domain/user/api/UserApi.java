package project.ec2session.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.common.auth.CustomUserDetails;
import project.ec2session.domain.user.dto.UserReq;

@Tag(name = "[사용자 관련 API]", description = "사용자 관련 API")
public interface UserApi {

    @Operation(
            summary = "로그인한 사용자 정보 조회",
            description = """
            사용자 단일 정보 조회 시도 (인증된 사용자만 접근 가능) 

            Authorization 헤더에 아래 형식으로 JWT 토큰을 포함해 요청해야 합니다:

            Authorization: Bearer {Access-Token}
            """,
            security = @SecurityRequirement(name = "")
    )
//@Operation(summary = "로그인한 사용자 정보 조회", description = "정보 조회 시도 (인증된 사용자만 접근 가능)")
@ApiResponses({
            @ApiResponse(responseCode = "200", description = "정보 조회 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                    	"userId": 1,
                                        "username": "test",
                                        "nickname": "테스터"
                                    }
                                    """)
                    })),
        @ApiResponse(responseCode = "403", description = "JWT 토큰 오류로 인한 실패",
                content = @Content(mediaType = "application/json", examples = {
                        @ExampleObject(value = """
                                    {
                                        "status": "403",
                                        "message": "토큰 입력 부분을 다시 확인 바랍니다."
                                    }
                                    """)
                })),
            @ApiResponse(responseCode = "404", description = "정보 조회 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": "404",
                                        "message": "존재하지 않는 회원입니다."
                                    }
                                    """)
                    }))
    })
    ResponseEntity<?> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(
            summary = "전체 사용자 정보 조회",
            description = """
            전체 사용자 정보 조회 시도 (인증된 사용자만 접근 가능) 

            Authorization 헤더에 아래 형식으로 JWT 토큰을 포함해 요청해야 합니다:

            Authorization: Bearer {Access-Token}
            """,
            security = @SecurityRequirement(name = "")
    )
//@Operation(summary = "전체 사용자 정보 조회", description = "전체 사용자 정보 조회 시도 (인증된 사용자만 접근 가능)")
@ApiResponses({
            @ApiResponse(responseCode = "200", description = "전체 사용자 정보 조회 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    [
                                             {
                                                 "userId": 1,
                                                 "username": "test1",
                                                 "nickname": "테스터1"
                                             },
                                             {
                                                 "userId": 2,
                                                 "username": "test2",
                                                 "nickname": "테스터2"
                                             },
                                             {
                                                 "userId": 3,
                                                 "username": "test3",
                                                 "nickname": "테스터3"
                                             }
                                    ]

                                    """)
                    }))
    })
    ResponseEntity<?> getAllUser();

    @Operation(
            summary = "사용자 정보 수정",
            description = """
            사용자 정보 수정 시도 (인증된 사용자만 접근 가능)

            Authorization 헤더에 아래 형식으로 JWT 토큰을 포함해 요청해야 합니다:

            Authorization: Bearer {Access-Token}
            """,
            security = @SecurityRequirement(name = "") //Bearer Authentication
    )
//@Operation(summary = "로그인한 사용자 정보 수정", description = "정보 수정 시도 (인증된 사용자만 접근 가능)")
@ApiResponses({
            @ApiResponse(responseCode = "200", description = "정보 수정 성공!",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "nickname": "새로운 닉네임"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "400", description = "닉네임 누락으로 인한 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": "400",
                                        "message": "닉네임은 필수 입력 값입니다."
                                    }
                                    """)
                    })),
        @ApiResponse(responseCode = "404", description = "정보 조회 실패",
                content = @Content(mediaType = "application/json", examples = {
                        @ExampleObject(value = """
                                    {
                                        "status": "404",
                                        "message": "존재하지 않는 회원입니다."
                                    }
                                    """)
                }))
    })
    ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @RequestBody @Valid UserReq.UpdateInfo request);
}
