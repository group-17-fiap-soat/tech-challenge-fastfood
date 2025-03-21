package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dao.PaymentDAO
import tech.challenge.fastfood.fastfood.common.dto.response.PaymentResponseV1
import tech.challenge.fastfood.fastfood.entities.PaymentAssociation
import tech.challenge.fastfood.fastfood.entities.PaymentData
import java.util.*

object PaymentMapper {


    fun toEntity(dao: PaymentDAO) =
        PaymentData(
            id = dao.id,
            externalId = dao.externalId,
            orderId = dao.orderId,
            totalAmount = dao.totalAmount,
            paymentMethod = dao.paymentMethod,
            paymentStatus = dao.paymentStatus,
            paymentDate = dao.paymentDate,
            createdAt = dao.createdAt,
            updatedAt = dao.updatedAt
        )

    fun toDao(entity: PaymentData) =
        PaymentDAO(
            id = entity.id,
            externalId = entity.externalId,
            orderId = entity.orderId,
            totalAmount = entity.totalAmount,
            paymentMethod = entity.paymentMethod,
            paymentStatus = entity.paymentStatus,
            paymentDate = entity.paymentDate,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun associationToResponse(association: PaymentAssociation)=
         PaymentResponseV1(
           id = association.paymentData.id,
           externalId = association.paymentData.externalId,
           data = association.data
        )

}
