package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dao.OrderItemDAO
import tech.challenge.fastfood.fastfood.common.dto.request.CreateOrderItemRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.OrderItemResponseV1
import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import tech.challenge.fastfood.fastfood.entities.OrderItem
import tech.challenge.fastfood.fastfood.entities.Product

object OrderItemMapper {

    fun fromRequestToEntity(requestV1: CreateOrderItemRequestV1) =
        OrderItem(
            product = Product(id = requestV1.productId),
            quantity = requestV1.quantity
        )

    fun toEntity(dao: OrderItemDAO) =
        OrderItem(
            id = dao.id,
            product = ProductMapper.toEntity(dao.product!!),
            orderId = dao.orderId,
            quantity = dao.quantity,
            createdAt = dao.createdAt,
            updatedAt = dao.updatedAt
        )

    fun toDao(entity: OrderItem) =
        OrderItemDAO(
            id = entity.id,
            product = ProductMapper.toDao(entity.product!!),
            orderId = entity.orderId,
            quantity = entity.quantity,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun toOrderItemResponseV1(entity: OrderItem) =
        OrderItemResponseV1(
            id = entity.id,
            productId = entity.product?.id,
            category = CategoryEnum.valueOf(entity.product?.category!!),
            quantity = entity.quantity,
            price = entity.product.price
        )

}