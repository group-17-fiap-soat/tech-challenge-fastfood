package tech.challenge.fastfood.fastfood.application.service.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Customer
import tech.challenge.fastfood.fastfood.usecases.customer.CreateCustomerUseCase
import tech.challenge.fastfood.fastfood.usecases.customer.GetCustomerByCpfUseCase
import tech.challenge.fastfood.fastfood.usecases.customer.GetCustomerByIdUseCase
import java.util.*

class CustomerServiceImplTest {

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
        val customer = Customer(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.save(customer)).thenReturn(customer)
        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(null)
        `when`(customerGatewayInterface.findByEmail("joao@example.com")).thenReturn(null)

        val result = createCustomerUseCase.execute(customer)

        assertEquals(customer.cpf, result.cpf)
        assertEquals(customer.name, result.name)
        assertEquals(customer.email, result.email)
        verify(customerGatewayInterface).save(customer)
    }

    @Test
    fun `createCustomer - falha CPF ausente`() {
        val customer = Customer(
            cpf = null,
            name = "João",
            email = "joao@example.com"
        )

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customer)
        }

        assertEquals("CPF tem que ser preenchido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF inválido`() {
        val customer = Customer(
            cpf = "12345",
            name = "João",
            email = "joao@example.com"
        )

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customer)
        }

        assertEquals("CPF inválido.", exception.message)
    }

    @Test
    fun `createCustomer - falha CPF já cadastrado`() {
        val customer = Customer(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(customer)

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customer)
        }

        assertEquals("CPF já cadastrado.", exception.message)
    }

    @Test
    fun `createCustomer - falha Email já cadastrado`() {
        val customer = Customer(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByEmail("joao@example.com")).thenReturn(customer)

        val exception = assertThrows<InvalidCustomerDataException> {
            createCustomerUseCase.execute(customer)
        }

        assertEquals("Email já cadastrado.", exception.message)
    }

    @Test
    fun `getCustomerByCpf - sucesso`() {
        val customer = Customer(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )

        `when`(customerGatewayInterface.findByCpf("12345678901")).thenReturn(customer)

        val result = getCustomerByCpfUseCase.execute("12345678901")

        assertEquals(customer.cpf, result?.cpf)
        assertEquals(customer.name, result?.name)
        assertEquals(customer.email, result?.email)
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
        val customer = Customer(
            cpf = "12345678901",
            name = "João",
            email = "joao@example.com"
        )
        val customerId = UUID.randomUUID()

        `when`(customerGatewayInterface.findById(customerId)).thenReturn(customer)

        val result = getCustomerByIdUseCase.execute(customerId)

        assertEquals(customer.cpf, result?.cpf)
        assertEquals(customer.name, result?.name)
        assertEquals(customer.email, result?.email)
    }

    @Test
    fun `getCustomerById - falha Cliente não encontrado`() {
        val customerId = UUID.randomUUID()

        `when`(customerGatewayInterface.findById(customerId)).thenReturn(null)

        val result = getCustomerByIdUseCase.execute(customerId)

        assertNull(result)
    }
}
