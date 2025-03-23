package tech.challenge.fastfood.fastfood

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
@OpenAPIDefinition(
    servers = [Server(url = "/", description = "Default Server URL")]
)
@SpringBootApplication
@EnableJpaAuditing
@EntityScan
@EnableJpaRepositories
@EnableTransactionManagement
class FastfoodApplication

fun main(args: Array<String>) {
    runApplication<FastfoodApplication>(*args)
}
