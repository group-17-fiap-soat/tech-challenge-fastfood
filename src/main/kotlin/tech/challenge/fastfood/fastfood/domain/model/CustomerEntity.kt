package tech.challenge.fastfood.fastfood.domain.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.OffsetDateTime
import java.util.UUID

@Entity(name = "tb_customer")
data class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "cpf", length = 11)
    val cpf: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "email")
    val email: String? = null,

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: OffsetDateTime? = null,

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
)
