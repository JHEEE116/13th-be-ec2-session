package project.ec2session.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.domain.user.dto.UserReq;

@Tag(name = "[인증 관련 API]", description = "인증 관련 API")
public interface AuthApi {

    @Operation(summary = "로그인", description = "로그인 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "성공 결과", value = """
                                    http://3.39.193.186:8080/api/v1/auth/sign-in - POST
                                    {
                                    	"accessToken" : "<accessToken>"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "404", description = "로그인 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "존재하지 않는 사용자", value = """
                                        {
                                            "status": "404",
                                            "message": "정보를 정확히 입력해주세요."
                                        }
                                    """),
                            @ExampleObject(name = "비밀번호 틀림", value = """
                                        {
                                            "status": "404",
                                            "message": "잘못된 비밀번호입니다."
                                        }
                                    """)
                    }))
    })
    ResponseEntity<?> signIn(
            @RequestBody @Valid UserReq.SignInDto request
    );

    @Operation(summary = "회원가입", description = "회원가입 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    http://3.39.193.186:8080/api/v1/auth/sign-up - POST
                                    {
                                    	"userId" : "1"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "400", description = "회원가입 유효성 검사 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                        "password": "비밀번호는 필수 입력 값입니다.",
                                        "nickname": "닉네임은 필수 입력 값입니다.",
                                        "username": "아이디는 필수 입력 값입니다."
                                    }
                                    """)
                    }))
    })
    ResponseEntity<?> signUp(@RequestBody @Valid UserReq.SignUpDto request);
}
