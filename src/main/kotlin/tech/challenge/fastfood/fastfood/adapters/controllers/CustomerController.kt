package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.adapters.controllers.operation.CustomerOperation
import tech.challenge.fastfood.fastfood.common.dtos.request.AuthCustomerRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.usecases.customer.CreateCustomerUseCase
import tech.challenge.fastfood.fastfood.usecases.customer.GetCustomerByCpfUseCase
import tech.challenge.fastfood.fastfood.usecases.customer.GetCustomerByIdUseCase
import java.util.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val createCustomerUseCase: CreateCustomerUseCase,
    private val getCustomerByCpf: GetCustomerByCpfUseCase,
    private val getCustomerById: GetCustomerByIdUseCase
) : CustomerOperation {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createCustomer(
        @RequestBody request: CreateCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1> {
        val customer = CustomerMapper.createCustomerRequestToDto(request)
        val createdCustomer = createCustomerUseCase.execute(customer)
        val response = CustomerMapper.toCustomerResponseV1(createdCustomer)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }


    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    override fun authenticate(
        @RequestBody
        authRequest: AuthCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1> {
        val customer = getCustomerByCpf.execute(
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
        val customer = getCustomerById.execute(id)
        return if (customer != null) {
            val response = CustomerMapper.toCustomerResponseV1(customer)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }


}
