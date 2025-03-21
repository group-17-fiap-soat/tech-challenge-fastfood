package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.usecases.payment.GetPaymentStatusUseCase
import tech.challenge.fastfood.fastfood.usecases.payment.ProcessPaymentUseCase

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    val getPaymentStatusUseCase: GetPaymentStatusUseCase,
    val processPaymentUseCase: ProcessPaymentUseCase
) {


    @GetMapping("/status/{paymentId}")
    fun getPaymentStatus(@PathVariable paymentId: Long): ResponseEntity<Map<String, String>> {
        val status = getPaymentStatusUseCase.execute(paymentId)
        return ResponseEntity.ok(mapOf("status" to status))
    }

    @PostMapping("/webhook")
    fun receiveWebhook(@RequestBody payload: Map<String, Any>): ResponseEntity<String> {
        val action = payload["action"] as? String
        val paymentId = (payload["data"] as? Map<*, *>)?.get("id") as? Long
        requireNotNull(paymentId)
        requireNotNull(action)

        processPaymentUseCase.execute(paymentId, action)
        return ResponseEntity.ok("Webhook received")
    }
}