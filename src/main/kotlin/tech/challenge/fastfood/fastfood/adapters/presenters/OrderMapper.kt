package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dao.OrderDAO
import tech.challenge.fastfood.fastfood.common.dto.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.OrderResponseV1
import tech.challenge.fastfood.fastfood.entities.Order

object OrderMapper {

    fun requestToEntity(requestV1: CreateOrderRequestV1) =
        Order(
            orderItems = requestV1.orderItems.map(OrderItemMapper::fromRequestToEntity)
        )

    fun toEntity(dao: OrderDAO) =
        Order(
            id = dao.id,
            orderNumber = dao.orderNumber,
            customerId = dao.customerId,
            status = dao.status,
            orderDate = dao.orderDate,
            finishedDate = dao.finishedDate,
            createdAt = dao.createdAt,
            updatedAt = dao.updatedAt
        )

    fun toDao(entity: Order) =
        OrderDAO(
            id = entity.id,
            orderNumber = entity.orderNumber,
            customerId = entity.customerId,
            status = entity.status,
            orderDate = entity.orderDate,
            finishedDate = entity.finishedDate,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun toOrderResponseV1(entity: Order) =
        OrderResponseV1(
            id = entity.id,
            payment = entity.payment?.let(PaymentMapper::associationToResponse),
            orderNumber = entity.orderNumber,
            customerId = entity.customerId,
            orderItems = entity.orderItems.map(OrderItemMapper::toOrderItemResponseV1),
            status = entity.status,
            totalPrice = entity.totalPrice
        )
}