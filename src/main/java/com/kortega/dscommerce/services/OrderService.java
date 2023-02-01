package com.kortega.dscommerce.services;

import com.kortega.dscommerce.dto.OrderDTO;
import com.kortega.dscommerce.dto.OrderItemDTO;
import com.kortega.dscommerce.entities.*;
import com.kortega.dscommerce.repositories.OrderItemRepository;
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
    private OrderRepository repository;
    @Autowired
    private ProductRespository productRespository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new ResourNotFoundException("Recurso nao encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
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
            OrderItem item = new OrderItem(order,product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);

    }
}

