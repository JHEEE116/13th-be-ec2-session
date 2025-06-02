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
                                    {
                                    	"accessToken" : "<accessToken>"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "401", description = "로그인 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "사용자 ID 또는 비밀번호 오류", value = """
                                        {
                                            "status": "401",
                                            "message": "사용자 ID 또는 비밀번호를 정확히 입력해주세요! "
                                        }
                                    """)
                    }))
    })
    ResponseEntity<?> signIn(
            @RequestBody @Valid UserReq.SignInDto request
    );

    @Operation(summary = "회원가입", description = "회원가입 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                                    {
                                    	"userId" : "1"
                                    }
                                    """)
                    })),
            @ApiResponse(
                    responseCode = "400",
                    description = "회원가입 유효성 검사 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(name = "필수 항목 누락 또는 형식 오류", value = """
                    {
                        "username": "올바른 이메일 형식이어야 합니다."
                        "password": "비밀번호 형식이 올바르지 않습니다. 8자 이상, 대소문자 포함, 숫자 및 특수문자(@$!%*?&#) 포함"
                        "username": "아이디는 필수 입력 값입니다."
                        "password": "비밀번호는 필수 입력 값입니다.",
                        "nickname": "닉네임은 필수 입력 값입니다.",
                    }
                    """)
                    })
            )

//            @ApiResponse(responseCode = "400", description = "회원가입 유효성 검사 실패",
//                    content = @Content(mediaType = "application/json", examples = {
//                            @ExampleObject(name = "회원가입 유효성 검사 실패",
//                                    value = """
//                                    {
//                                        "password": "비밀번호는 필수 입력 값입니다.",
//                                        "nickname": "닉네임은 필수 입력 값입니다.",
//                                        "username": "아이디는 필수 입력 값입니다."
//                                    }
//                                    """
//                            )//name 공백일 경우 ui 출력X
//                    })),
//
//            @ApiResponse(responseCode = "400", description = "회원가입 유효성 검사 실패",
//                    content = @Content(mediaType = "application/json", examples = {
//                            @ExampleObject (name = "이메일 형식 오류", value = """
//                                    {
//                                        "username": "올바른 이메일 형식이어야 합니다."
//                                    }
//                                    """
//                            )
//                    })),
//
//            @ApiResponse(responseCode = "400", description = "회원가입 유효성 검사 실패",
//                    content = @Content(mediaType = "application/json", examples = {
//                            @ExampleObject (name = "비밀번호 형식 오류", value = """
//                                    {
//                                        "password": "비밀번호 형식이 올바르지 않습니다. 8자 이상, 대소문자 포함, 숫자 및 특수문자(@$!%*?&#) 포함"
//                                    }
//                                    """
//                            )
//                    }))
    })
    ResponseEntity<?> signUp(@RequestBody @Valid UserReq.SignUpDto request);
}
