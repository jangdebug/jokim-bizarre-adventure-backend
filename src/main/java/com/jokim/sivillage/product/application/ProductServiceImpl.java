package com.jokim.sivillage.product.application;


import com.jokim.sivillage.product.domain.Product;
import com.jokim.sivillage.product.dto.out.DailyHotProductResponseDto;
import com.jokim.sivillage.product.dto.out.ProductResponseDto;
import com.jokim.sivillage.product.infrastructure.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto getProductById(long id) {

        Product product = productRepository.findById(id)
                  .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public List<DailyHotProductResponseDto> getDailyHotProduct() {

        return List.of();
    }
}
