package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderItemRepository;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@Transactional
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private static final String FIND_ALL = "from OrderItem";
    private static final String FIND_BY_ORDER_ID = "from OrderItem where orderId = :orderId";
    private static final String DELETE_BY_ID = "delete from OrderItem where orderId = :orderId";
    private static final String COUNT_TOTAL_COST = "select sum(quantity * price) from OrderItem where orderId = :orderId";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<OrderItem> findById(Long key) {
        log.info("OrderItem with id {} found", key);
        return Optional.ofNullable(manager.find(OrderItem.class, key));
    }

    @Override
    public List<OrderItem> findAll() {
        log.info("Created a list of orderItems matching the search criteria");
        return manager.createQuery(FIND_ALL, OrderItem.class).getResultList();
    }

    @Override
    public void save(OrderItem entity) {
        log.info("Trying to update a row with a orderItem in the database");
        if (entity.getId() != null) {
            log.info("Update a orderItem");
            manager.merge(entity);
        } else {
            log.info("Create a orderItem");
            manager.persist(entity);
        }
    }

    @Override
    public boolean delete(Long key) {
        log.info("Trying to delete a row with a orderItem in the database");
        OrderItem orderItem = manager.find(OrderItem.class, key);
        if (orderItem == null) {
            log.warn("Updated rows (orderItem): 0");
            return false;
        }
        TypedQuery<OrderItem> query = manager.createQuery(DELETE_BY_ID, OrderItem.class);
        query.setParameter("orderId", key);
        log.info("OrderItem is deleted");
        return query.executeUpdate() == 1;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        log.info("Find list orderItems by order id");
        TypedQuery<OrderItem> query = manager.createQuery(FIND_BY_ORDER_ID, OrderItem.class);
        query.setParameter("orderId", OrderItem.class);
        return query.getResultList();
    }

    @Override
    public BigDecimal findTotalCost(Long orderId) {
        log.info("Trying find order total cost");
        TypedQuery<BigDecimal> query = manager.createQuery(COUNT_TOTAL_COST, BigDecimal.class);
        query.setParameter("orderId", orderId);
        BigDecimal totalCost =  query.getSingleResult();
        if (totalCost != null) {
            return totalCost;
        } else {
            log.warn("Returned null from totalCost SQL query");
            throw new NotFoundException("Unable to calculate order amount");
        }
    }
}
