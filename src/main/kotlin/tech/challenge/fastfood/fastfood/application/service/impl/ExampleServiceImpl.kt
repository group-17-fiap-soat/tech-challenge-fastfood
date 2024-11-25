package tech.challenge.fastfood.fastfood.application.service.impl

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.application.dto.ExampleDto
import tech.challenge.fastfood.fastfood.application.service.ExampleService
import tech.challenge.fastfood.fastfood.domain.port.ExampleRepositoryPort
import tech.challenge.fastfood.fastfood.infra.mapper.ExampleMapper

@Service
class ExampleServiceImpl(
    val exampleRepositoryPort: ExampleRepositoryPort
): ExampleService {
    override fun createExample(exampleDto: ExampleDto): ExampleDto {
        val savedEntity =  exampleRepositoryPort.save(ExampleMapper.toEntity(exampleDto));
        return ExampleMapper.toDto(savedEntity);
    }
    //TODO lógica aqui :)
}