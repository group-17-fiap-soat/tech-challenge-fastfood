package tech.challenge.fastfood.fastfood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages = ["tech.challenge.fastfood.fastfood.domain.model"])  // Pacote onde as entidades estão localizadas
@EnableJpaRepositories(basePackages = ["tech.challenge.fastfood.fastfood.infra.adapters.repositories"])  // Pacote onde os repositórios estão

class FastfoodApplication

fun main(args: Array<String>) {
    runApplication<FastfoodApplication>(*args)
}
