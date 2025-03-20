package tech.challenge.fastfood.fastfood.application.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.usecases.customer.CreateCustomerUseCase
import tech.challenge.fastfood.fastfood.usecases.customer.GetCustomerByCpfUseCase
import tech.challenge.fastfood.fastfood.usecases.customer.GetCustomerByIdUseCase
import java.util.*

class CustomerDtoServiceImplTest {

    private lateinit var customerGatewayInterface: CustomerGatewayInterface
    private lateinit var createCustomerUseCase: CreateCustomerUseCase
    private lateinit var getCustomerByCpfUseCase: GetCustomerByCpfUseCase
    private lateinit var getCustomerByIdUseCase: GetCustomerByIdUseCase

    @BeforeEach
    fun setUp() {
        customerGatewayInterface = mock()
        createCustomerUseCase = CreateCustomerUseCase(customerGatewayInterface)
        getCustomerByCpfUseCase = GetCustomerByCpfUseCase(customerGatewayInterface)
        getCustomerByIdUseCase = GetCustomerByIdUseCase(customerGatewayInterface)
    }

    @Test
    fun `createCustomer - sucesso`() {
        val customerDto = CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )
        val savedEntity = CustomerMapper.toEntity(customerDto)

        `when`(customerGatewayInterface.save(customerDto)).thenReturn(savedEntity)
        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(null)
        `when`(customerGatewayInterface.findByEmail("joao@example.com")).thenReturn(null)

        val result = createCustomerUseCase.execute(customerDto)

        assertEquals(customerDto.cpf, result.cpf)
        assertEquals(customerDto.name, result.name)
        assertEquals(customerDto.email, result.email)
        verify(customerGatewayInterface).save(customerDto)
    }

    @Test
    fun `createCustomer - falha CPF ausente`() {
        val customerDto = CustomerDto(
            cpf = null,
            name = "João",
            email = "joao@example.com"
        )

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customerDto)
        }

        assertEquals("CPF tem que ser preenchido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF inválido`() {
        val customerDto = CustomerDto(
            cpf = "12345",
            name = "João",
            email = "joao@example.com"
        )

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customerDto)
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF já cadastrado`() {
        val customerDto = CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(CustomerMapper.toEntity(customerDto))

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customerDto)
        }

        assertEquals("CPF já cadastrado.", exception.message)
    }

    @Test
    fun `createCustomer - falha Email já cadastrado`() {
        val customerDto = CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByEmail("joao@example.com")).thenReturn(CustomerMapper.toEntity(customerDto))

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customerDto)
        }

        assertEquals("Email já cadastrado.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - sucesso`() {
        val customerDto = CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(CustomerMapper.toEntity(customerDto))

        val result = getCustomerByCpfUseCase.execute("12345678901")

        assertEquals(customerDto.cpf, result?.cpf)
        assertEquals(customerDto.name, result?.name)
        assertEquals(customerDto.email, result?.email)
    }

    @Test
    fun `getCustomerByCpf - falha CPF inválido`() {
        val exception = assertThrows<InvalidCustomerDataException> {
            getCustomerByCpfUseCase.execute("12345")
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - falha Cliente não encontrado`() {
        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(null)

        val result = getCustomerByCpfUseCase.execute("12345678901")

        assertNull(result)
    }

    @Test
    fun `getCustomerById - sucesso`() {
        val customerDto = CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )
        val customerId = UUID.randomUUID()

        `when`(customerGatewayInterface.findById(customerId)).thenReturn(CustomerMapper.toEntity(customerDto))

        val result = getCustomerByIdUseCase.execute(customerId)

        assertEquals(customerDto.cpf, result?.cpf)
        assertEquals(customerDto.name, result?.name)
        assertEquals(customerDto.email, result?.email)
    }

    @Test
    fun `getCustomerById - falha Cliente não encontrado`() {
        val customerId = UUID.randomUUID()

        `when`(customerGatewayInterface.findById(customerId)).thenReturn(null)

        val result = getCustomerByIdUseCase.execute(customerId)

        assertNull(result)
    }
}
