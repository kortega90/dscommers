package com.kortega.dscommerce.repositories;

import com.kortega.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRespository extends JpaRepository <Product,Long> {

}
