package tech.challenge.fastfood.fastfood.infra.adapters.controllers

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.challenge.fastfood.fastfood.application.service.ExampleService
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateExampleRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CreateExampleResponseV1
import tech.challenge.fastfood.fastfood.infra.mapper.ExampleMapper

@RestController
@RequestMapping("/examples")
class ExampleController(
    private val exampleService: ExampleService
) {
    @PostMapping
    fun postExample(
        @Valid
        @RequestBody
        createExampleRequest: CreateExampleRequestV1
    ): ResponseEntity<CreateExampleResponseV1> {
        val example = exampleService.createExample(ExampleMapper.toDto(createExampleRequest))
        return ResponseEntity.ok(ExampleMapper.toCreateExampleResponseV1(example))
    }
}