package com.storemanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.storemanagement.dto.ProductDTO;
import com.storemanagement.repo.ProductRepository;
import com.storemanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/product")
public class ProductController {
	private ProductService productService;
	private ProductRepository productRepository;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public ProductController(ProductService productService, ProductRepository productRepository) {
		this.productService = productService;
		this.productRepository=productRepository;
	}

	@PostMapping
	public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDto) {
		return productService.createProduct(productDto);
	}
	
	@PutMapping
	public ProductDTO updateProduct(@RequestBody @Valid ProductDTO productDto) {
			try {
				if (productDto.getId() == null) throw new Exception("Product id not provided!");
				return productService.updateProduct(productDto);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@GetMapping("/{id}")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}

	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("/all/{ids}")
	public List<ProductDTO> getProductsByIds(@PathVariable List<Long> ids){

		return productRepository.findAllById(ids)
				.stream()				
                .map(productEntity -> mapper.map(productEntity, ProductDTO.class))
                .collect(Collectors.toList());

		
	}
	
	

}
