package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.OrderDTO;
import com.kortega.dscommerce.entities.Order;
import com.kortega.dscommerce.repositories.OrderRepository;
import com.kortega.dscommerce.services.exceptions.ResourNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository respository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
       Order order = respository.findById(id).orElseThrow(() -> new ResourNotFoundException("Recurso nao encontrado"));
        return new OrderDTO(order);

    }
}

