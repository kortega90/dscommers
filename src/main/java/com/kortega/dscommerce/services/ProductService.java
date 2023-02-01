package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.CategoryDTO;
import com.kortega.dscommerce.dto.ProductDTO;
import com.kortega.dscommerce.dto.ProductMinDTO;
import com.kortega.dscommerce.entities.Category;
import com.kortega.dscommerce.entities.Product;
import com.kortega.dscommerce.repositories.ProductRespository;
import com.kortega.dscommerce.services.exceptions.DataBaseException;
import com.kortega.dscommerce.services.exceptions.ResourNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRespository respository;

    @Transactional(readOnly = true)
    /*public ProductDTO findById(Long id) {
        Optional<Product> result = respository.findById(id);
        Product product = result.orElseThrow(() -> new RuntimeException("Recurso nao encontrado"));
        ProductDTO dto = new ProductDTO(product);
        return dto;*/

    public ProductDTO findById(Long id) {
        Product product = respository.findById(id).orElseThrow(() -> new ResourNotFoundException("Recurso nao encontrado"));
        return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findByAll(String name, Pageable pageable) {
        Page<Product> result = respository.searchByName(name, pageable);
        return result.map(x -> new ProductMinDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product prod = new Product();
        prod.setName(dto.getName());
        prod.setDescription(dto.getDescription());
        prod.setPrice(dto.getPrice());
        prod.setImgUrl(dto.getImgUrl());

        prod.getCategories().clear();
        for (CategoryDTO obj: dto.getCategories()){
            Category cat = new Category();
            cat.setId(obj.getId());
            prod.getCategories().add(cat);
        }
        /*
        prod = respository.save(prod);
        return new ProductDTO(prod);
        */
        return new ProductDTO(respository.save(prod));
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product prod = respository.getReferenceById(id);
            /* outra opçaõ utilizando o metodo*/
            copyDtoToProd(dto, prod);
            return new ProductDTO(respository.save(prod));
        } catch (EntityNotFoundException e) {
            throw new ResourNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional (propagation = Propagation.SUPPORTS )
    public void delete(Long id) {
        try {
            respository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourNotFoundException("Recurso não encontrado");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Data de integridade referencial");
        }
    }


    private void copyDtoToProd(ProductDTO dto, Product prod) {
        prod.setName(dto.getName());
        prod.setDescription(dto.getDescription());
        prod.setPrice(dto.getPrice());
        prod.setImgUrl(dto.getImgUrl());

        prod.getCategories().clear();
        for (CategoryDTO obj: dto.getCategories()){
            Category cat = new Category();
            cat.setId(obj.getId());
            prod.getCategories().add(cat);
        }
    }

}
