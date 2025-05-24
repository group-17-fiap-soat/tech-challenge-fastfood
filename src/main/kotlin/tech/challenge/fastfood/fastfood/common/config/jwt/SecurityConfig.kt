package tech.challenge.fastfood.fastfood.common.config.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import tech.challenge.fastfood.fastfood.common.enums.TokenRoleEnum

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Value("\${jwt.secret}")
    private lateinit var lambdaSecret: String

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                val customer = TokenRoleEnum.CUSTOMER.name
                val admin = TokenRoleEnum.ADMIN.name
                it
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                    .requestMatchers("/health-check").permitAll()
                    .requestMatchers("/api/customers/**").permitAll()
                    .requestMatchers("/api/customers/auth").permitAll()

                    .requestMatchers(HttpMethod.GET, "/api/products").hasAnyRole(customer, admin)
                    .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyRole(customer, admin)
                    .requestMatchers(HttpMethod.GET, "/api/orders/*").hasAnyRole(customer, admin)
                    .requestMatchers(HttpMethod.POST, "/api/orders").hasAnyRole(customer, admin)
                    .requestMatchers("/api/payments/**").hasAnyRole(customer, admin)
                    .requestMatchers("/api/**").hasRole(admin)

                    .anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(lambdaSecret), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}