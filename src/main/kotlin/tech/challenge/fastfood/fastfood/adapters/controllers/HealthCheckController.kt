package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("health-check")
class HealthCheckController {

    @GetMapping
    fun healthCheck(): String {
        //TODO fazer health check com enum no futuro
        val message = "Healthy 🥗"

        val htmlTemplate = String(Files.readAllBytes(Paths.get("src/main/resources/templates/healthCheck.html")))

        return htmlTemplate.replace("{{message}}", message)
    }
}