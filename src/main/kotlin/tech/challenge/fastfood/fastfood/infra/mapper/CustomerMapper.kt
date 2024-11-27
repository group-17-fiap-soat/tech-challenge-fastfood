package tech.challenge.fastfood.fastfood.infra.mapper

import tech.challenge.fastfood.fastfood.domain.model.CustomerEntity
import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CustomerResponseV1

object CustomerMapper {
    fun toDto(entity: CustomerEntity) = CustomerDto(
        id = entity.id,
        cpf = entity.cpf,
        name = entity.name,
        email = entity.email,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )

    fun createCustomerRequestToDto(requestV1: CreateCustomerRequestV1) = CustomerDto(
        cpf = requestV1.cpf,
        name = requestV1.name,
        email = requestV1.email
    )

    fun toEntity(dto: CustomerDto) = CustomerEntity(
        id = dto.id,
        cpf = dto.cpf,
        name = dto.name,
        email = dto.email,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
    )

    fun toCustomerResponseV1(dto: CustomerDto) = CustomerResponseV1(
        cpf = dto.cpf,
        name = dto.name,
        email = dto.email
    )
}
