package tech.challenge.fastfood.fastfood.domain.port;

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.domain.model.ExampleEntity

@Component
interface ExampleRepositoryPort {
    fun save(exampleEntity: ExampleEntity): ExampleEntity
}
