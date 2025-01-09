package tech.challenge.fastfood.fastfood.domain.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "product")
data class ProductEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    var price: BigDecimal,

    @Column(nullable = false)
    var category: String,

    var imageUrl: String? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()
)
