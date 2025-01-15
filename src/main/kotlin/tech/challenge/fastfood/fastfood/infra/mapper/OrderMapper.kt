package tech.challenge.fastfood.fastfood.infra.mapper

import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.domain.model.OrderEntity
import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.OrderResponseV1

object OrderMapper {
    fun toDto(entity: OrderEntity) = OrderDto(
        id = entity.id,
        orderNumber = entity.orderNumber,
        customer = CustomerMapper.toDto(entity.customer),
        status = entity.status,
        orderDate = entity.orderDate,
        finishedAt = entity.finishedDate,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )

    private val orderNumberSequence = generateSequence(1) { it + 1 }.iterator()

    fun createOrderRequestToDto(requestV1: CreateOrderRequestV1) = OrderDto(
        orderNumber = orderNumberSequence.next().toLong(),
        customer = CustomerDto(id = requestV1.idCustomer),
        status = StatusOrderEntity.RECEIVED
    )

    fun toEntity(dto: OrderDto) = OrderEntity(
        id = dto.id,
        orderNumber = dto.orderNumber,
        customer = CustomerMapper.toEntity(dto.customer),
        status = dto.status,
        orderDate = dto.orderDate,
        finishedDate = dto.finishedAt,
        createdAt = dto.createdAt,
        updatedAt = dto.updatedAt
    )

    fun toOrderResponseV1(dto: OrderDto) = OrderResponseV1(
        orderNumber = dto.orderNumber,
        customer = CustomerMapper.toCustomerResponseV1(dto.customer),
        status = dto.status,
    )
}