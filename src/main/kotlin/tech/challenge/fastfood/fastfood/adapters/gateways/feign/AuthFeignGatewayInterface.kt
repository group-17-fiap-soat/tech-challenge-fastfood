package tech.challenge.fastfood.fastfood.adapters.gateways.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import tech.challenge.fastfood.fastfood.common.dto.request.AuthFeignRequest

@FeignClient(name = "authFeignClient", url = "\${AUTH_SERVICE_URL}")
interface AuthFeignGatewayInterface {

    @PostMapping("/auth", consumes = ["application/json"])
    fun authenticate(@RequestBody authRequest: AuthFeignRequest): String
}
