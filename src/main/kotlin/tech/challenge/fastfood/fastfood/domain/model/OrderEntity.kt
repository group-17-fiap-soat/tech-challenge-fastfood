package tech.challenge.fastfood.fastfood.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "tb_order")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "number", insertable = false, updatable = false, unique = true, nullable = false)
    var orderNumber: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    val customer: CustomerEntity? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: StatusOrderEntity? = null,

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
