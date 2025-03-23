package tech.challenge.fastfood.fastfood.common.dao

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "tb_customer")
@EntityListeners(AuditingEntityListener::class)
data class CustomerDAO(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "cpf", length = 11)
    val cpf: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "email")
    val email: String? = null,

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
)
