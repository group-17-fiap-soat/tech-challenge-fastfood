package tech.challenge.fastfood.fastfood.adapters.controllers

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.common.dto.request.WebhookRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.PaymentStatusResponseV1
import tech.challenge.fastfood.fastfood.usecases.payment.GetPaymentStatusUseCase
import tech.challenge.fastfood.fastfood.usecases.payment.ProcessPaymentUseCase
import java.util.UUID

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    val getPaymentStatusUseCase: GetPaymentStatusUseCase,
    val processPaymentUseCase: ProcessPaymentUseCase
) {

    @GetMapping("/status/{paymentId}")
    fun getPaymentStatus(@PathVariable paymentId: UUID): ResponseEntity<PaymentStatusResponseV1> {
        val status = getPaymentStatusUseCase.execute(paymentId)
            ?: throw EntityNotFoundException("Payment with given id not found")

        return ResponseEntity.ok(PaymentStatusResponseV1(status))
    }

    @PostMapping("/webhook")
    fun receiveWebhook(@RequestBody request: WebhookRequestV1): ResponseEntity<String> {
        request.data["id"]?.toLong()?.let { externalId -> processPaymentUseCase.execute(externalId, request.action) }
        return ResponseEntity.ok("Webhook received, payment with externalId: ${request.data["id"]} processed")
    }
}

