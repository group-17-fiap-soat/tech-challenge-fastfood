package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.adapters.controllers.operation.ProductOperation
import tech.challenge.fastfood.fastfood.adapters.presenters.ProductMapper
import tech.challenge.fastfood.fastfood.common.dtos.request.CreateProductRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.request.UpdateProductRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.response.ProductResponseV1
import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import tech.challenge.fastfood.fastfood.usecases.product.CreateProductUseCase
import tech.challenge.fastfood.fastfood.usecases.product.FindAllProductUseCase
import tech.challenge.fastfood.fastfood.usecases.product.PutProductUseCase
import tech.challenge.fastfood.fastfood.usecases.product.RemoveProductByIdUseCase
import java.util.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val createProductUseCase: CreateProductUseCase,
    private val putProductUseCase: PutProductUseCase,
    private val findAllProductUseCase: FindAllProductUseCase,
    private val removeProductByIdUseCase: RemoveProductByIdUseCase
): ProductOperation {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createProduct(
        @RequestBody request: CreateProductRequestV1
    ): ResponseEntity<ProductResponseV1> {
        val product = ProductMapper.fromRequestToEntity(request)
        val response = createProductUseCase.execute(product).let(ProductMapper::toProductResponseV1)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    override fun putProduct(
        @RequestBody request: UpdateProductRequestV1
    ): ResponseEntity<ProductResponseV1> {
        val product = ProductMapper.fromRquestToEntity(request)
        val response = putProductUseCase.execute(product).let(ProductMapper::toProductResponseV1)
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun findAll(
        @RequestParam category: CategoryEnum?
    ): ResponseEntity<List<ProductResponseV1>> {
        val productList =  findAllProductUseCase.execute(category = category?.toString()).map(ProductMapper::toProductResponseV1)
        return ResponseEntity.status(200).body(productList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun removeProductById(
        @PathVariable id: String
    ): ResponseEntity<Void> {
        removeProductByIdUseCase.execute(UUID.fromString(id))
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}