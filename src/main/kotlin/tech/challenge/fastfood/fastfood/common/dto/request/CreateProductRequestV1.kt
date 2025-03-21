package tech.challenge.fastfood.fastfood.common.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import java.math.BigDecimal

data class CreateProductRequestV1(
    @field:NotNull(message = "Nome não pode estar vazio.")
    @field:Pattern(regexp = "^[A-Za-z0-9_ ]+$", message = "O nome do item deve ser alfanumérico e pode incluir espaços.")
    val name: String,

    @field:Pattern(regexp = "^[A-Za-z0-9_ ]+$", message = "O nome do item deve ser alfanumérico e pode incluir espaços.")
    val description: String?,

    @field:NotNull(message = "Campo não pode estar vazio.")
    val price: BigDecimal,

    @field:NotNull(message = "Campo não pode estar vazio.")
    val category: CategoryEnum,

    val imageUrl: String?
)