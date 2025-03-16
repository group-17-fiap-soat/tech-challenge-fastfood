package tech.challenge.fastfood.fastfood.application.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import java.util.*

class CustomerServiceImplTest {

    private lateinit var customerGatewayInterface: CustomerGatewayInterface
    private lateinit var customerService: CustomerServiceImpl

    @BeforeEach
    fun setUp() {
        customerGatewayInterface = mock()
        customerService = CustomerServiceImpl(customerGatewayInterface)
    }

    @Test
    fun `createCustomer - sucesso`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )
        val savedEntity = CustomerMapper.toEntity(customerDto)

        `when`(customerGatewayInterface.save(customerDto)).thenReturn(savedEntity)
        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(null)
        `when`(customerGatewayInterface.findByEmail("joao@example.com")).thenReturn(null)

        val result = customerService.createCustomer(customerDto)

        assertEquals(customerDto.cpf, result.cpf)
        assertEquals(customerDto.name, result.name)
        assertEquals(customerDto.email, result.email)
        verify(customerGatewayInterface).save(customerDto)
    }

    @Test
    fun `createCustomer - falha CPF ausente`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = null,
            name = "João",
            email = "joao@example.com"
        )

        val exception = assertThrows<tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("CPF tem que ser preenchido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF inválido`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = "12345",
            name = "João",
            email = "joao@example.com"
        )

        val exception = assertThrows<tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF já cadastrado`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(CustomerMapper.toEntity(customerDto))

        val exception = assertThrows<tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("CPF já cadastrado.", exception.message)
    }

    @Test
    fun `createCustomer - falha Email já cadastrado`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByEmail("joao@example.com")).thenReturn(CustomerMapper.toEntity(customerDto))

        val exception = assertThrows<tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("Email já cadastrado.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - sucesso`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(CustomerMapper.toEntity(customerDto))

        val result = customerService.getCustomerByCpf("12345678901")

        assertEquals(customerDto.cpf, result?.cpf)
        assertEquals(customerDto.name, result?.name)
        assertEquals(customerDto.email, result?.email)
    }

    @Test
    fun `getCustomerByCpf - falha CPF inválido`() {
        val exception = assertThrows<tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException> {
            customerService.getCustomerByCpf("12345")
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - falha Cliente não encontrado`() {
        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(null)

        val result = customerService.getCustomerByCpf("12345678901")

        assertNull(result)
    }

    @Test
    fun `getCustomerById - sucesso`() {
        val customerDto = tech.challenge.fastfood.fastfood.common.dtos.CustomerDto(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )
        val customerId = UUID.randomUUID()

        `when`(customerGatewayInterface.findById(customerId)).thenReturn(CustomerMapper.toEntity(customerDto))

        val result = customerService.getCustomerById(customerId)

        assertEquals(customerDto.cpf, result?.cpf)
        assertEquals(customerDto.name, result?.name)
        assertEquals(customerDto.email, result?.email)
    }

    @Test
    fun `getCustomerById - falha Cliente não encontrado`() {
        val customerId = UUID.randomUUID()

        `when`(customerGatewayInterface.findById(customerId)).thenReturn(null)

        val result = customerService.getCustomerById(customerId)

        assertNull(result)
    }
}
