package tech.challenge.fastfood.fastfood.infra.adapters.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.application.service.ProductService
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.operation.ProductOperation
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateProductRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.UpdateProductRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.ProductResponseV1
import tech.challenge.fastfood.fastfood.infra.mapper.ProductMapper
import java.util.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
): ProductOperation {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createProduct(
        @RequestBody request: CreateProductRequestV1
    ): ResponseEntity<ProductResponseV1> {
        val product = ProductMapper.createProductRequestToDto(request)
        val response = productService.createProduct(product).let(ProductMapper::toProductResponseV1)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    override fun putProduct(
        @RequestBody request: UpdateProductRequestV1
    ): ResponseEntity<ProductResponseV1> {
        val product = ProductMapper.updateProductRequestToDto(request)
        val response = productService.putProduct(product).let(ProductMapper::toProductResponseV1)
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun findAllByCategory(
        @RequestParam category: String
    ): ResponseEntity<List<ProductResponseV1>> {
        val productList =  productService.findAllByCategory(category).map(ProductMapper::toProductResponseV1)
        return ResponseEntity.status(200).body(productList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun removeProductById(
        @PathVariable id: String
    ): ResponseEntity<Void> {
        productService.removeProductById(UUID.fromString(id))
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}