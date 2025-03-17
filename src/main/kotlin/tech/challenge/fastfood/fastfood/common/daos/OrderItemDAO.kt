package tech.challenge.fastfood.fastfood.common.daos

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.*

@Table(name = "tb_order_item")
@Entity(name = "tb_order_item")
data class OrderItemDAO (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    val product: ProductDAO? = null,

    @Column(nullable = false)
    var quantity: Int? = null,

    @Column(name = "order_id", nullable = false)
    var orderId: UUID? = null,

    @CreationTimestamp
    @Column(name = "created_at",  updatable = false)
    var createdAt: OffsetDateTime? = OffsetDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = OffsetDateTime.now()
)