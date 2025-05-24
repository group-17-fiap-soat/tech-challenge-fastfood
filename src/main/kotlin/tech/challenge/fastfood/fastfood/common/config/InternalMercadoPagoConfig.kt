package tech.challenge.fastfood.fastfood.common.config

import com.mercadopago.MercadoPagoConfig
import com.mercadopago.client.payment.PaymentClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class InternalMercadoPagoConfig {

    @PostConstruct
    fun initialize() {
        val accessToken = System.getenv("MERCADO_PAGO_ACCESS_TOKEN")
        MercadoPagoConfig.setAccessToken(accessToken)
    }

    @Bean
    fun paymentClient(): PaymentClient {
        return PaymentClient()
    }
}