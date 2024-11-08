import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
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
        .info(
            Info()
                .title("FastFood Application API")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("http://swagger.io/terms/")
        )
}