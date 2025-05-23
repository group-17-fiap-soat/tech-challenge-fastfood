package tech.challenge.fastfood.fastfood.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
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
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                it
                    // Público
                    .requestMatchers("/api/**").hasRole("ADMIN")
                    .requestMatchers("/api/customers").permitAll()
                    .requestMatchers("/api/customers/**").permitAll()
                    .requestMatchers("/api/payments**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/orders").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("CLIENT")
                    .requestMatchers("/api/payments/**").hasRole("CLIENT")

                    .anyRequest().permitAll()
            }
            .addFilterBefore(JwtAuthenticationFilter(lambdaSecret), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
