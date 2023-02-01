package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.OrderDTO;
import com.kortega.dscommerce.dto.OrderItemDTO;
import com.kortega.dscommerce.entities.Order;
import com.kortega.dscommerce.entities.OrderStatus;
import com.kortega.dscommerce.entities.Product;
import com.kortega.dscommerce.entities.User;
import com.kortega.dscommerce.repositories.OrderRepository;
import com.kortega.dscommerce.repositories.ProductRespository;
import com.kortega.dscommerce.services.exceptions.ResourNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository respository;
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
       Order order = respository.findById(id).orElseThrow(() -> new ResourNotFoundException("Recurso nao encontrado"));
        return new OrderDTO(order);

    }
    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.authenticated();
        order.setClient(user);
        for (OrderItemDTO itemDTO: dto.getItems()){
            Product product = productRespository.getReferenceById(itemDTO.getProductId());
        }

    }
}

