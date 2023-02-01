package com.kortega.dscommerce.repositories;

import com.kortega.dscommerce.entities.OrderItem;
import com.kortega.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
