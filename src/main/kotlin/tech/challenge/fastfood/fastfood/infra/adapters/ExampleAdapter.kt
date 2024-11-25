package tech.challenge.fastfood.fastfood.infra.adapters;

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.domain.model.ExampleEntity
import tech.challenge.fastfood.fastfood.domain.port.ExampleRepositoryPort
import tech.challenge.fastfood.fastfood.infra.adapters.repositories.ExampleJPAInterface

@Component
class ExampleAdapter(
    val exampleJPAInterface: ExampleJPAInterface
) :  ExampleRepositoryPort {
    override fun save(exampleEntity: ExampleEntity): ExampleEntity {
        return exampleJPAInterface.save(exampleEntity)
    }
}
