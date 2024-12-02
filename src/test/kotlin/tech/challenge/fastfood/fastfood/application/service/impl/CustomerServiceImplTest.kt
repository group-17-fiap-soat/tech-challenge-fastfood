package tech.challenge.fastfood.fastfood.application.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.domain.port.CustomerRepositoryPort
import tech.challenge.fastfood.fastfood.infra.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.infra.mapper.CustomerMapper
import java.util.*

class CustomerServiceImplTest {

    private lateinit var customerRepositoryPort: CustomerRepositoryPort
    private lateinit var customerService: CustomerServiceImpl

    @BeforeEach
    fun setUp() {
        customerRepositoryPort = mock()
        customerService = CustomerServiceImpl(customerRepositoryPort)
    }

    @Test
    fun `createCustomer - sucesso`() {
        val customerDto = CustomerDto(cpf = "12345678901", name = "João", email = "joao@example.com")
        val savedEntity = CustomerMapper.toEntity(customerDto)

        `when`(customerRepositoryPort.save(customerDto)).thenReturn(savedEntity)
        `when`(customerRepositoryPort.findByCpf("12345678901")).thenReturn(null)
        `when`(customerRepositoryPort.findByEmail("joao@example.com")).thenReturn(null)

        val result = customerService.createCustomer(customerDto)

        assertEquals(customerDto.cpf, result.cpf)
        assertEquals(customerDto.name, result.name)
        assertEquals(customerDto.email, result.email)
        verify(customerRepositoryPort).save(customerDto)
    }

    @Test
    fun `createCustomer - falha CPF ausente`() {
        val customerDto = CustomerDto(cpf = null, name = "João", email = "joao@example.com")

        val exception = assertThrows<InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("CPF tem que ser preenchido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF inválido`() {
        val customerDto = CustomerDto(cpf = "12345", name = "João", email = "joao@example.com")

        val exception = assertThrows<InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF já cadastrado`() {
        val customerDto = CustomerDto(cpf = "12345678901", name = "João", email = "joao@example.com")

        `when`(customerRepositoryPort.findByCpf("12345678901")).thenReturn(CustomerMapper.toEntity(customerDto))

        val exception = assertThrows<InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("CPF já cadastrado.", exception.message)
    }

    @Test
    fun `createCustomer - falha Email já cadastrado`() {
        val customerDto = CustomerDto(cpf = "12345678901", name = "João", email = "joao@example.com")

        `when`(customerRepositoryPort.findByEmail("joao@example.com")).thenReturn(CustomerMapper.toEntity(customerDto))

        val exception = assertThrows<InvalidCustomerDataException> {
            customerService.createCustomer(customerDto)
        }

        assertEquals("Email já cadastrado.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - sucesso`() {
        val customerDto = CustomerDto(cpf = "12345678901", name = "João", email = "joao@example.com")

        `when`(customerRepositoryPort.findByCpf("12345678901")).thenReturn(CustomerMapper.toEntity(customerDto))

        val result = customerService.getCustomerByCpf("12345678901")

        assertEquals(customerDto.cpf, result?.cpf)
        assertEquals(customerDto.name, result?.name)
        assertEquals(customerDto.email, result?.email)
    }

    @Test
    fun `getCustomerByCpf - falha CPF inválido`() {
        val exception = assertThrows<InvalidCustomerDataException> {
            customerService.getCustomerByCpf("12345")
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - falha Cliente não encontrado`() {
        `when`(customerRepositoryPort.findByCpf("12345678901")).thenReturn(null)

        val result = customerService.getCustomerByCpf("12345678901")

        assertNull(result)
    }

    @Test
    fun `getCustomerById - sucesso`() {
        val customerDto = CustomerDto(cpf = "12345678901", name = "João", email = "joao@example.com")
        val customerId = UUID.randomUUID()

        `when`(customerRepositoryPort.findById(customerId)).thenReturn(CustomerMapper.toEntity(customerDto))

        val result = customerService.getCustomerById(customerId)

        assertEquals(customerDto.cpf, result?.cpf)
        assertEquals(customerDto.name, result?.name)
        assertEquals(customerDto.email, result?.email)
    }

    @Test
    fun `getCustomerById - falha Cliente não encontrado`() {
        val customerId = UUID.randomUUID()

        `when`(customerRepositoryPort.findById(customerId)).thenReturn(null)

        val result = customerService.getCustomerById(customerId)

        assertNull(result)
    }
}
