package com.belhard.bookstore.data.dao;

import java.util.List;

public interface OrderItemDao extends CrudDao<OrderItemDao, Long>{
    List<OrderItemDao> findByOrderId (Long orderId);
}
