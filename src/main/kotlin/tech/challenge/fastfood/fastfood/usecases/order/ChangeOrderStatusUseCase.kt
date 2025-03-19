package tech.challenge.fastfood.fastfood.usecases.order

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper
import tech.challenge.fastfood.fastfood.common.daos.OrderDAO
import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import java.util.*

@Service
class ChangeOrderStatusUseCase(
    private val orderGatewayInterface: OrderGatewayInterface
) {
    fun execute(id: UUID, status: OrderStatusEnum?): OrderDto {
        val order = orderGatewayInterface.findById(id)
            ?: throw EntityNotFoundException("Pedido com o id ${id} não encontrado")
        return changeStatus(order, status);
    }

    private fun changeStatus(order: OrderDAO, status: OrderStatusEnum?): OrderDto {
        val statusList = OrderStatusEnum.entries.filter { it.priority != null }.sortedByDescending { it.priority }
        val currentIndex = statusList.indexOf(order.status)

        val newStatus = status ?: statusList.getOrNull(currentIndex + 1) ?: order.status

        val updatedOrder = orderGatewayInterface.save(
            OrderMapper.toDto(order.copy(status = newStatus))
        )
        return OrderMapper.toDto(updatedOrder);
    }
}