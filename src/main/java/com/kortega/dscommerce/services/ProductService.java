package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.ProductDTO;
import com.kortega.dscommerce.entities.Product;
import com.kortega.dscommerce.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRespository respository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = respository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;

        /* ou *****
        public ProductDTO findById(Long id) {
        Product product = respository.findById(id).get();
      return = new ProductDTO(product);
         */
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findByAll(Pageable pageable) {
        Page<Product> result = respository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product prod = new Product();
        prod.setName(dto.getName());
        prod.setDescription(dto.getDescription());
        prod.setPrice(dto.getPrice());
        prod.setImgUrl(dto.getImgUrl());

        /*
        prod = respository.save(prod);
        return new ProductDTO(prod);
        */
        return new ProductDTO(respository.save(prod));
    }

    @Transactional
    public ProductDTO Update(Long id, ProductDTO dto) {

        Product prod = respository.getReferenceById(id);
        /* outra opçaõ utilizando o metodo*/
        copyDtoToProd (dto, prod)
        return new ProductDTO(respository.save(prod));
    }

    private void copyDtoToProd(ProductDTO dto, Product prod) {
        prod.setName(dto.getName());
        prod.setDescription(dto.getDescription());
        prod.setPrice(dto.getPrice());
        prod.setImgUrl(dto.getImgUrl());
    }
}
