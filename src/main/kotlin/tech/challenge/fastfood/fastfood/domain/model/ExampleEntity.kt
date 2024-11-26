package tech.challenge.fastfood.fastfood.domain.model;

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID


@Entity(name = "example")
class ExampleEntity (
    @Id
    val id: UUID? = null
)
