package tech.challenge.fastfood.fastfood.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(name = "tb_product")
data class ProductEntity(

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
