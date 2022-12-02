package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.ProductDTO;
import com.kortega.dscommerce.entities.Product;
import com.kortega.dscommerce.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRespository respository;

@Transactional(readOnly = true)
    public ProductDTO findById (Long id) {
        Optional <Product> result = respository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return  dto;
    }
}
