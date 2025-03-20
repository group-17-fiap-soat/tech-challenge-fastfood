package tech.challenge.fastfood.fastfood.common.dao

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import tech.challenge.fastfood.fastfood.common.enums.PaymentStatusEnum
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "tb_payment")
@Table(name = "tb_payment")
data class PaymentDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID? = null,

    @Column(name = "external_id", updatable = false, nullable = false)
    val externalId: Long? = null,

    @Column(name = "order_id")
    val orderId: UUID?,

    @Column(name = "total_amount", nullable = false)
    val totalAmount: BigDecimal,

    @Column(name = "payment_method", nullable = false)
    val paymentMethod: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    val paymentStatus: PaymentStatusEnum,

    @Column(name = "payment_date", nullable = false, updatable = false)
    @CreationTimestamp
    val paymentDate: OffsetDateTime? = OffsetDateTime.now(),

    @CreationTimestamp
    @Column(name = "created_at", nullable = false,updatable = false)
    var createdAt: OffsetDateTime? = OffsetDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false,updatable = false)
    var updatedAt: OffsetDateTime? = OffsetDateTime.now()
)