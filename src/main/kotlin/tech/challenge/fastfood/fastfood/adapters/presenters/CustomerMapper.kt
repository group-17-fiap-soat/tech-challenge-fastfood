package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.daos.CustomerDAO
import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.dtos.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.response.CustomerResponseV1

object CustomerMapper {
    fun toDto(entity: CustomerDAO?) =
        CustomerDto(
            id = entity?.id,
            cpf = entity?.cpf,
            name = entity?.name,
            email = entity?.email,
            createdAt = entity?.createdAt,
            updatedAt = entity?.updatedAt
        )

    fun createCustomerRequestToDto(requestV1: CreateCustomerRequestV1?) =
        CustomerDto(
            cpf = requestV1?.cpf,
            name = requestV1?.name,
            email = requestV1?.email
        )

    fun toEntity(dto: CustomerDto?) =
        CustomerDAO(
            id = dto?.id,
            cpf = dto?.cpf,
            name = dto?.name,
            email = dto?.email,
            createdAt = dto?.createdAt,
            updatedAt = dto?.updatedAt
        )

    fun toCustomerResponseV1(dto: CustomerDto?) =
        CustomerResponseV1(
            id = dto?.id,
            cpf = dto?.cpf,
            name = dto?.name,
            email = dto?.email
        )
}
