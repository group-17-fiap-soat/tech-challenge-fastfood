package tech.challenge.fastfood.fastfood.common.dto.request

data class WebhookRequestV1(
    val action: String,
    val data: Map<String, String>
)