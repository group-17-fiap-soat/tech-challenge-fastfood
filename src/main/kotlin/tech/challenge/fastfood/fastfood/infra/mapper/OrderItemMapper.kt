package tech.challenge.fastfood.fastfood.infra.mapper

import tech.challenge.fastfood.fastfood.application.dto.OrderItemDto
import tech.challenge.fastfood.fastfood.application.dto.ProductDto
import tech.challenge.fastfood.fastfood.domain.model.OrderItemEntity
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateOrderItemRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.OrderItemResponseV1

object OrderItemMapper {

    fun createOrderItemRequestToDto(requestV1: CreateOrderItemRequestV1) = OrderItemDto(
        product = ProductDto(id = requestV1.productId),
        quantity = requestV1.quantity
    )

    fun toEntity(orderItemDto: OrderItemDto) = OrderItemEntity(
        id = orderItemDto.id,
        product = ProductMapper.toEntity(orderItemDto.product!!),
        orderId = orderItemDto.orderId,
        quantity = orderItemDto.quantity,
        createdAt = orderItemDto.createdAt,
        updatedAt = orderItemDto.updatedAt
    )

    fun toDto(orderItemEntity: OrderItemEntity) = OrderItemDto(
        id = orderItemEntity.id,
        product = ProductMapper.toDto(orderItemEntity.product!!),
        orderId = orderItemEntity.orderId,
        quantity = orderItemEntity.quantity,
        createdAt = orderItemEntity.createdAt,
        updatedAt = orderItemEntity.updatedAt
    )

    fun toOrderItemResponseV1(orderItemDto: OrderItemDto) =
        OrderItemResponseV1(
            id = orderItemDto.id,
            productId = orderItemDto.product?.id,
            quantity = orderItemDto.quantity,
            price = orderItemDto.product?.price
        )



}