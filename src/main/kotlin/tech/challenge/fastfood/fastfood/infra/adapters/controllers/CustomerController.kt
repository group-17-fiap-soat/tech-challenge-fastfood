package tech.challenge.fastfood.fastfood.infra.adapters.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.application.service.CustomerService
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.operation.CustomerOperation
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.AuthCustomerRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.infra.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.infra.mapper.CustomerMapper
import java.util.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) : CustomerOperation {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createCustomer(
        @RequestBody request: CreateCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1> {
        val customer = CustomerMapper.createCustomerRequestToDto(request)
        val createdCustomer = customerService.createCustomer(customer)
        val response = CustomerMapper.toCustomerResponseV1(createdCustomer)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }


    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    override fun authenticate(
        @RequestBody
        authRequest: AuthCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1> {
        val customer = customerService.getCustomerByCpf(
            checkNotNull(authRequest.cpf) {
                throw InvalidCustomerDataException("CPF tem que ser preenchido.")
            }
        )
        return if (customer != null) {
            val response = CustomerMapper.toCustomerResponseV1(customer)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    }


    @GetMapping("/{id}")
    override fun getCustomerById(
        @PathVariable
        id: UUID
    ): ResponseEntity<CustomerResponseV1> {
        val customer = customerService.getCustomerById(id)
        return if (customer != null) {
            val response = CustomerMapper.toCustomerResponseV1(customer)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }


}
