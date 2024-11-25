package tech.challenge.fastfood.fastfood.domain.model;

import jakarta.persistence.Entity
import jakarta.persistence.Id


@Entity
class ExampleEntity (
    @Id
    val id: Long? = null
)
