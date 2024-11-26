package tech.challenge.fastfood.fastfood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class FastfoodApplication

fun main(args: Array<String>) {
	runApplication<FastfoodApplication>(*args)
}
