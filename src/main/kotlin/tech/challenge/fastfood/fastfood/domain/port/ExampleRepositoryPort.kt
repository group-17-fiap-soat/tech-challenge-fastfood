package tech.challenge.fastfood.fastfood.domain.port;

import tech.challenge.fastfood.fastfood.domain.model.ExampleEntity

interface ExampleRepositoryPort {
    fun save(exampleEntity: ExampleEntity): ExampleEntity
}
