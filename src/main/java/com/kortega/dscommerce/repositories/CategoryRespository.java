package com.kortega.dscommerce.repositories;

import com.kortega.dscommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository <Category,Long> {

}
