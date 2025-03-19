package tech.challenge.fastfood.fastfood.adapters.controllers
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.usecases.PaymentService
import java.math.BigDecimal

@RestController
@RequestMapping("/api/pix")
class PaymentController(val mpPixService: PaymentService) {

    data class CriarPixRequest(val valor: BigDecimal, val emailCliente: String)

    @PostMapping("/criar")
    fun criarPagamentoPix(@RequestBody requisicao: CriarPixRequest): ResponseEntity<PaymentService.PixPagamentoDTO> {
        val resultado = mpPixService.criarPagamentoPix(requisicao.valor, requisicao.emailCliente)
        return ResponseEntity.ok(resultado)
    }

    @GetMapping("/status/{paymentId}")
    fun consultarStatusPagamento(@PathVariable paymentId: Long): ResponseEntity<Map<String, String>> {
        val status = mpPixService.consultarStatusPagamento(paymentId)
        return ResponseEntity.ok(mapOf("status" to status))
    }
}