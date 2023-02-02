package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.CategoryDTO;
import com.kortega.dscommerce.entities.Category;
import com.kortega.dscommerce.repositories.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository respository;


    @Transactional(readOnly = true)
    public List<CategoryDTO> findByAll() {
        List<Category> result = respository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

}
