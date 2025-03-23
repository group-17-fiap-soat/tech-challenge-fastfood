package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dao.CustomerDAO
import tech.challenge.fastfood.fastfood.common.dto.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.entities.Customer

object CustomerMapper {
    fun fromDaoToEntity(dao: CustomerDAO?) = Customer(
            id = dao?.id,
            cpf = dao?.cpf,
            name = dao?.name,
            email = dao?.email,
            createdAt = dao?.createdAt,
            updatedAt = dao?.updatedAt
        )

    fun fromRequestToEntity(requestV1: CreateCustomerRequestV1?) =
        Customer(
            cpf = requestV1?.cpf,
            name = requestV1?.name,
            email = requestV1?.email
        )

    fun toDAO(entity: Customer) =
        CustomerDAO(
            id = entity.id,
            cpf = entity.cpf,
            name = entity.name,
            email = entity.email,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun toCustomerResponseV1(entity: Customer) =
        CustomerResponseV1(
            id = entity.id,
            cpf = entity.cpf,
            name = entity.name,
            email = entity.email
        )
}
