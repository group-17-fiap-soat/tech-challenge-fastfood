package tech.challenge.fastfood.fastfood.common.dto.request

import jakarta.validation.constraints.NotNull

data class WebhookRequestV1(
    @field:NotNull(message = "Campo não poder estar vazio.")
    val action: String,

    val data: Map<String, String>
)