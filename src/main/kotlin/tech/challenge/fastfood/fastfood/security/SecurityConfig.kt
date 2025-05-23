package tech.challenge.fastfood.fastfood.security

import com.seuapp.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${jwt.secret}")
    private lateinit var lambdaSecret: String

    @Bean
    fun jwtAuthFilter(): JwtAuthenticationFilter {
        val cognitoPublicKey = CognitoPublicKeyProvider.getPublicKey()
        return JwtAuthenticationFilter(lambdaSecret, cognitoPublicKey)
    }

    @Bean
    fun filterChain(http: HttpSecurity, jwtAuthFilter: JwtAuthenticationFilter): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth").permitAll()
                   // .requestMatchers("api/orders/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
