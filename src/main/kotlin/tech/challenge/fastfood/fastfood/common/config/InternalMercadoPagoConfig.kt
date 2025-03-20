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
        MercadoPagoConfig.setAccessToken("TEST-2888144853903955-031900-fb822b506960da281a9a1d332a0e23a7-1568273131")
    }

    @Bean
    fun paymentClient(): PaymentClient {
        return PaymentClient()
    }
}