package com.example.ecom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.ecom.dao.ProductDAO;
import com.example.ecom.dto.ProductDTO;
import com.example.ecom.model.Product;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;
    private ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductDAO productDAO, ModelMapper modelMapper) {
        this.productDAO = productDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}

