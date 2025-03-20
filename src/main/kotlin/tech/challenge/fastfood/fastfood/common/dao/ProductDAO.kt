package tech.challenge.fastfood.fastfood.common.dao

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "tb_product")
data class ProductDAO(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String? = null,

    var description: String? = null,

    @Column(nullable = false)
    var price: BigDecimal? = null,

    @Column(nullable = false)
    var category: String? = null,

    var imageUrl: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false,updatable = false)
    var createdAt: OffsetDateTime? = OffsetDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false,updatable = false)
    var updatedAt: OffsetDateTime? = OffsetDateTime.now()
)
