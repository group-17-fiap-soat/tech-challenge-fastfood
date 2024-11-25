package tech.challenge.fastfood.fastfood.application.service

import tech.challenge.fastfood.fastfood.application.dto.ExampleDto

interface ExampleService {
    fun createExample(exampleDto: ExampleDto): ExampleDto
}