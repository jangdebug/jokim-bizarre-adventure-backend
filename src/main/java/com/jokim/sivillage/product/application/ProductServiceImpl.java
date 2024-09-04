package com.jokim.sivillage.product.application;


import com.jokim.sivillage.product.domain.Product;
import com.jokim.sivillage.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.product.dto.out.FilteredProductDto;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.product.infrastructure.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProductById(long id) {

        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public List<DailyHotProductResponseDto> getDailyHotProducts() {

        return List.of();
    }

    @Override
    public List<ProductResponseDto> getFilteredProducts(Long sizeId, Long colorId, Long etcId) {
        log.info("before productRepository");
        List<Product> products = productRepository.findBySizeAndColorAndEtc(sizeId, colorId, etcId);
        log.info("List<Product> products[0] {}", products.get(0).toString());
        ModelMapper modelMapper = new ModelMapper();
        List<ProductResponseDto> productResponseDtos = products.stream()
            .map(product -> modelMapper.map(product, ProductResponseDto.class))
            .collect(Collectors.toList());

        log.info("productResponseDtos {}", productResponseDtos);

        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getRandomProducts(Integer count) {
        List<Product> products = productRepository.findRandomProducts(count);
        ModelMapper modelMapper = new ModelMapper();
        List<ProductResponseDto> productResponseDtos = products.stream()
            .map(product -> modelMapper.map(product, ProductResponseDto.class))
            .collect(Collectors.toList());
        return productResponseDtos;
    }
}