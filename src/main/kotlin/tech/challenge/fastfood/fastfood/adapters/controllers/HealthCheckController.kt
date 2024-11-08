package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(name = "health-check")
class HealthCheckController {

    @GetMapping("health")
    fun healthCheck() = "Alive"
}