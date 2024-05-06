package com.storemanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.storemanagement.constant.CurrencyEnum;
import com.storemanagement.dto.ProductDTO;
import com.storemanagement.entity.ProductEntity;
import com.storemanagement.repo.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepositoryMock;

    private Mapper mapper = DozerBeanMapperBuilder.buildDefault();


    @Test
    public void testCreateProduct() {

        ProductDTO productDTO = generateProductDto();
        ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
        when(productRepositoryMock.save(any())).thenReturn(productEntity);
        when(productRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(productEntity));


        ProductDTO savedProduct = productService.createProduct(productDTO);

        assertEquals(productDTO.getName(), savedProduct.getName());
        assertEquals(productDTO.getCurrencyId(), savedProduct.getCurrencyId());
        assertEquals(productDTO.getPrice(), savedProduct.getPrice());


    }

    ProductDTO generateProductDto() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("some name");
        productDTO.setDescription("some desc");
        productDTO.setSupplier("some supplier");
        productDTO.setCurrencyId(CurrencyEnum.RON);
        productDTO.setPrice(40.0);

        return productDTO;

    }



}
