package tech.challenge.fastfood.fastfood.common.config.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import tech.challenge.fastfood.fastfood.common.enums.TokenRoleEnum

class JwtAuthenticationFilter(
    private val lambdaSecret: String,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        val token = header?.removePrefix("Bearer ")?.trim()

        if (!token.isNullOrBlank()) {
            val result = JwtTokenUtil.validateToken(
                token, lambdaSecret
            )

            if (result == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token")
                return
            }

            val (claims, role) = result
            val authorities = listOf(SimpleGrantedAuthority("ROLE_${role.name}"))
            val customer = if( role == TokenRoleEnum.CUSTOMER)  JwtTokenUtil.claimsToCustomer(claims, token) else  claims.subject
            val authentication = UsernamePasswordAuthenticationToken(customer, null, authorities)

            SecurityContextHolder.getContext().authentication = authentication
            request.setAttribute("claims", claims)
        }

        filterChain.doFilter(request, response)
    }
}