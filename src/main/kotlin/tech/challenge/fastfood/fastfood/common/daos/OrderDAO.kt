package tech.challenge.fastfood.fastfood.common.daos

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Generated
import org.hibernate.annotations.UpdateTimestamp
import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "tb_order")
@Table(name = "tb_order")
data class OrderDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "number", insertable = false, updatable = false, unique = true, nullable = false)
    @Generated
    var orderNumber: Long? = null,

    @Column(name = "customer_id", nullable = true)
    val customerId: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum? = null,

    @CreationTimestamp
    @Column(name = "order_date")
    val orderDate: OffsetDateTime? = null,

    @Column(name = "finished_date")
    val finishedDate: OffsetDateTime? = null,

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
)
