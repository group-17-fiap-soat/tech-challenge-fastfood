package tech.challenge.fastfood.fastfood.adapters.gateways

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.gateways.feign.AuthFeignGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.dto.request.AuthFeignRequest
import tech.challenge.fastfood.fastfood.common.dto.response.AuthFeignResponse
import tech.challenge.fastfood.fastfood.entities.Customer

@Component
class AuthFeignGateway(
    private val authFeignGatewayInterface: AuthFeignGatewayInterface,
    private val objectMapper: ObjectMapper
) {

    fun getCustomerByCpf(cpf: String): Customer {
        val json = authFeignGatewayInterface.authenticate(AuthFeignRequest(cpf))
        val response = objectMapper.readValue(json, AuthFeignResponse::class.java)

        return CustomerMapper.fromAuthFeignResponse(response).copy(cpf = cpf)
    }
}
