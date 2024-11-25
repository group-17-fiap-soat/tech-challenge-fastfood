package tech.challenge.fastfood.fastfood.infra.mapper

import tech.challenge.fastfood.fastfood.application.dto.ExampleDto
import tech.challenge.fastfood.fastfood.domain.model.ExampleEntity
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateExampleRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CreateExampleResponseV1

object ExampleMapper {
    fun toDto(entity: ExampleEntity) = ExampleDto(id = entity.id!!)
    fun toDto(requestV1: CreateExampleRequestV1) = ExampleDto(id = requestV1.id)
    fun toEntity(dto: ExampleDto) = ExampleEntity(id = dto.id)
    fun toCreateExampleResponseV1(dto: ExampleDto) = CreateExampleResponseV1(id = dto.id)

}