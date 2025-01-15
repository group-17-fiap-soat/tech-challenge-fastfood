package tech.challenge.fastfood.fastfood.domain.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "order")
@Table(name = "tb_order")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "number")
    val orderNumber: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    val customer: CustomerEntity? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    val status: StatusOrderEntity = StatusOrderEntity.RECEIVED,

    @Column(name = "order_date")
    val orderDate: OffsetDateTime? = null,

    @Column(name = "finished_date")
    val finishedDate: OffsetDateTime? = null,

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
)
