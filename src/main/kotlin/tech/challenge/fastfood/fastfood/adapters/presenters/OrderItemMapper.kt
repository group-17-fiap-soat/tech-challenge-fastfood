package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto
import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO
import tech.challenge.fastfood.fastfood.common.dtos.request.CreateOrderItemRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.response.OrderItemResponseV1

object OrderItemMapper {

    fun createOrderItemRequestToDto(requestV1: CreateOrderItemRequestV1) =
        OrderItemDto(
            product = ProductDto(id = requestV1.productId),
            quantity = requestV1.quantity
        )

    fun toEntity(orderItemDto: OrderItemDto) =
        OrderItemDAO(
            id = orderItemDto.id,
            product = ProductMapper.toEntity(orderItemDto.product!!),
            orderId = orderItemDto.orderId,
            quantity = orderItemDto.quantity,
            createdAt = orderItemDto.createdAt,
            updatedAt = orderItemDto.updatedAt
        )

    fun toDto(orderItemDAO: OrderItemDAO) =
        OrderItemDto(
            id = orderItemDAO.id,
            product = ProductMapper.toDto(orderItemDAO.product!!),
            orderId = orderItemDAO.orderId,
            quantity = orderItemDAO.quantity,
            createdAt = orderItemDAO.createdAt,
            updatedAt = orderItemDAO.updatedAt
        )

    fun toOrderItemResponseV1(orderItemDto: OrderItemDto) =
        OrderItemResponseV1(
            id = orderItemDto.id,
            productId = orderItemDto.product?.id,
            category = orderItemDto.product?.category,
            quantity = orderItemDto.quantity,
            price = orderItemDto.product?.price
        )



}