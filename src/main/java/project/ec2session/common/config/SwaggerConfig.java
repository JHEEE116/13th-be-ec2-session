package project.ec2session.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Test App",
                description = "TestApp API 명세",
                version = "v1")
)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi OpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("TestApp API v1")
                .pathsToMatch(paths)
                .build();
    }

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes("Bearer Authentication",
//                        new SecurityScheme()
//                                .name("Authorization")
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
//                                .description("""
//                                    인증이 필요한 API 요청 시,
//                                    Authorization 헤더에 아래 형식으로 JWT 토큰을 포함해주세요.
//
//                                    Authorization: Bearer {access-token}
//                                    """)
//                ));
//    }

}
