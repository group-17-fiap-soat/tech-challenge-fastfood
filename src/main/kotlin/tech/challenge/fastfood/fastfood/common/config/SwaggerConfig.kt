import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(
        @Value("\${application-description}") appDescription: String?,
        @Value("\${application-version}") appVersion: String?
    ) = OpenAPI()
        .components(
            Components().addSecuritySchemes(
                "bearerAuth",
                SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )
        )
        .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
        .info(
            Info()
                .title("FastFood Application API")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("http://swagger.io/terms/")
        )
}