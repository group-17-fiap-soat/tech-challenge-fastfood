package tech.challenge.fastfood.fastfood.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.*

@Table(name = "tb_order_item")
@Entity(name = "tb_order_item")
data class OrderItemEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    val product: ProductEntity? = null,

    @Column(nullable = false)
    var quantity: Int? = null ,

    @Column(name = "order_id", nullable = false)
    var orderId: UUID? = null,

    @CreationTimestamp
    @Column(name = "created_at",  updatable = false)
    var createdAt: OffsetDateTime? = OffsetDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = OffsetDateTime.now()
)