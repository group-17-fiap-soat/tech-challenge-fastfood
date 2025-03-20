package tech.challenge.fastfood.fastfood.adapters.presenters

import tech.challenge.fastfood.fastfood.common.dao.ProductDAO
import tech.challenge.fastfood.fastfood.common.dto.request.CreateProductRequestV1
import tech.challenge.fastfood.fastfood.common.dto.request.UpdateProductRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.ProductResponseV1
import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import tech.challenge.fastfood.fastfood.entities.Product

object ProductMapper {

    fun toEntity(dao: ProductDAO) =
        Product(
            id = dao.id,
            name = dao.name,
            description = dao.description,
            price = dao.price,
            category = dao.category.toString(),
            imageUrl = dao.imageUrl,
            createdAt = dao.createdAt,
            updatedAt = dao.updatedAt
        )

    fun toDao(entity: Product) =
        ProductDAO(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            price = entity.price,
            category = entity.category.toString(),
            imageUrl = entity.imageUrl,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )

    fun fromRequestToEntity(requestV1: CreateProductRequestV1) =
        Product(
            name = requestV1.name,
            description = requestV1.description,
            price = requestV1.price,
            category = requestV1.category.toString(),
            imageUrl = requestV1.imageUrl
        )

    fun fromRquestToEntity(requestV1: UpdateProductRequestV1) =
        Product(
            id = requestV1.id,
            name = requestV1.name,
            description = requestV1.description,
            price = requestV1.price,
            category = requestV1.category.toString(),
            imageUrl = requestV1.imageUrl
        )

    fun toProductResponseV1(entity: Product) =
        ProductResponseV1(
            id = entity.id,
            name = entity.name!!,
            description = entity.description,
            price = entity.price!!,
            category = CategoryEnum.valueOf(entity.category!!),
            imageUrl = entity.imageUrl
        )
}