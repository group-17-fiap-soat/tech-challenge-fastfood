package tech.challenge.fastfood.fastfood.application.service

import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import java.util.*

interface OrderService {
    fun listOrders(): List<OrderDto>?
    fun getOrderById(id: UUID): OrderDto
    fun createOrder(orderDto: OrderDto): OrderDto
}