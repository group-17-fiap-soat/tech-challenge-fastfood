package tech.challenge.fastfood.fastfood.infra.mapper

import tech.challenge.fastfood.fastfood.application.dto.ProductDto
import tech.challenge.fastfood.fastfood.domain.model.ProductEntity
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateProductRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.UpdateProductRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.ProductResponseV1

object ProductMapper {
    fun toDto(entity: ProductEntity) = ProductDto(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        price = entity.price,
        category = entity.category,
        imageUrl = entity.imageUrl,
        createdAt = entity.createdAt,
        updatedAt = entity.updatedAt
    )

    fun toEntity(dto: ProductDto) = ProductEntity(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        price = dto.price,
        category = dto.category,
        imageUrl = dto.imageUrl,
        createdAt = dto.createdAt!!,
        updatedAt = dto.updatedAt!!
    )

    fun createProductRequestToDto(requestV1: CreateProductRequestV1) = ProductDto(
        name = requestV1.name,
        description = requestV1.description,
        price = requestV1.price,
        category = requestV1.category,
        imageUrl = requestV1.imageUrl
    )

    fun updateProductRequestToDto(requestV1: UpdateProductRequestV1) = ProductDto(
        id = requestV1.id,
        name = requestV1.name,
        description = requestV1.description,
        price = requestV1.price,
        category = requestV1.category,
        imageUrl = requestV1.imageUrl
    )

    fun toProductResponseV1(productDto: ProductDto) = ProductResponseV1(
        id = productDto.id,
        name = productDto.name,
        description = productDto.description,
        price = productDto.price,
        category = productDto.category,
        imageUrl = productDto.imageUrl
    )
}