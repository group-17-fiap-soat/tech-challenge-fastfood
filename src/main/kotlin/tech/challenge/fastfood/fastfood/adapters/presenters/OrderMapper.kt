package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.daos.OrderDAO
import tech.challenge.fastfood.fastfood.common.dtos.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.response.OrderResponseV1
import java.math.BigDecimal

object OrderMapper {
    fun toDto(entity: OrderDAO) =
        OrderDto(
            id = entity.id,
            orderNumber = entity.orderNumber,
            customerId = entity.customerId,
            status = entity.status,
            orderDate = entity.orderDate,
            finishedAt = entity.finishedDate,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )


    fun createOrderRequestToDto(requestV1: CreateOrderRequestV1) =
        OrderDto(
            customerId = requestV1.customerId,
            orderItems = requestV1.orderItems.map(OrderItemMapper::createOrderItemRequestToDto)
        )

    fun toEntity(dto: OrderDto) =
        OrderDAO(
            id = dto.id,
            orderNumber = dto.orderNumber,
            customerId = dto.customerId,
            status = dto.status,
            orderDate = dto.orderDate,
            finishedDate = dto.finishedAt,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt
        )

    fun toOrderResponseV1(dto: OrderDto) =
        OrderResponseV1(
            id = dto.id,
            orderNumber = dto.orderNumber,
            customerId = dto.customerId,
            orderItems = dto.orderItems.map(OrderItemMapper::toOrderItemResponseV1),
            status = dto.status,
            totalPrice = dto.calculateTotalPrice()
        )

    private fun OrderDto.calculateTotalPrice(): BigDecimal {
        return this.orderItems.fold(BigDecimal.ZERO) { acc, item ->
            acc + item.product?.price!!.multiply(item.quantity?.toBigDecimal())
        }
    }
}