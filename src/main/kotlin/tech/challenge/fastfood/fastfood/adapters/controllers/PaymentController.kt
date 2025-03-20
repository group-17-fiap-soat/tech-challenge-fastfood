package tech.challenge.fastfood.fastfood.adapters.controllers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.common.dto.request.CreatePaymentRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.PixPaymentResponseV1
import tech.challenge.fastfood.fastfood.usecases.payment.CreatePaymentUseCase
import tech.challenge.fastfood.fastfood.usecases.payment.GetPaymentStatusUseCase

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    val createPaymentUseCase: CreatePaymentUseCase,
    val getPaymentStatusUseCase: GetPaymentStatusUseCase
) {

    @PostMapping
    fun createPayment(@RequestBody request: CreatePaymentRequestV1): ResponseEntity<PixPaymentResponseV1> {
        val response = createPaymentUseCase.execute(request.value, request.email)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/status/{paymentId}")
    fun getPaymentStatus(@PathVariable paymentId: Long): ResponseEntity<Map<String, String>> {
        val status = getPaymentStatusUseCase.execute(paymentId)
        return ResponseEntity.ok(mapOf("status" to status))
    }

    @PostMapping("/webhook")
    fun receiveWebhook(@RequestBody payload: Map<String, Any>): ResponseEntity<String> {
//        val action = payload["action"] as? String
//        val paymentId = (payload["data"] as? Map<String, Any>)?.get("id") as? Long

//        if (paymentId != null && action == "payment.updated") {
//            val status = mpPixService.checkPaymentStatus(paymentId)
//            println("🔔 Payment $paymentId updated: $status")
//
//            // TODO: Process the payment (update DB, notify user, etc.)
//        }
        System.out.println("PAGOU PORRAAAAAAAAAAAAAAAAA")
        return ResponseEntity.ok("Webhook received")
    }
}