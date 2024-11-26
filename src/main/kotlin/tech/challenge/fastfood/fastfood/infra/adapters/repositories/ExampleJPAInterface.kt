package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.domain.model.ExampleEntity
import java.util.UUID

@Repository
interface ExampleJPAInterface : JpaRepository<ExampleEntity, UUID> {
}