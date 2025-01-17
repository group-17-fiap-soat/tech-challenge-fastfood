package tech.challenge.fastfood.fastfood.infra.mapper

import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.domain.model.OrderEntity
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.OrderResponseV1
import java.math.BigDecimal

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


    fun createOrderRequestToDto(requestV1: CreateOrderRequestV1) = OrderDto(
        customer = CustomerDto(id = requestV1.idCustomer),
        orderItems = requestV1.orderItems.map(OrderItemMapper::createOrderItemRequestToDto)
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
        id = dto.id,
        orderNumber = dto.orderNumber,
        idCustomer = dto.customer?.id,
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