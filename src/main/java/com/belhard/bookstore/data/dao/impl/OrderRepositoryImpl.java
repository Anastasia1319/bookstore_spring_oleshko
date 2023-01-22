package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderRepository;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.belhard.bookstore.data.entity.Order.Status.CANCELED;

@Repository
@Log4j2
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    private static final String FIND_ALL = "from Order";
    private static final String FIND_BY_ID = "from Order where id = :id";
    private static final String FIND_BY_USER_ID = "from Order where user = :userId";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Order> findById(Long key) {
        log.info("Order with id {} found", key);
        TypedQuery<Order> query = manager.createQuery(FIND_BY_ID, Order.class);
        query.setParameter("id", key);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Order> findAll() {
        log.info("Created a list of orders matching the search criteria");
        TypedQuery<Order> query = manager.createQuery(FIND_ALL, Order.class);
        return query.getResultList();
    }

    @Override
    public void save(Order entity) {
        log.info("Trying to update a row with a order in the database");
        if (entity.getId() != null) {
            log.info("Updated the order");
            manager.merge(entity);
        } else {
            log.info("Created the order");
            manager.persist(entity);
        }
    }

    @Override
    public boolean delete(Long key) {
        log.info("Trying to delete a row with a order in the database");
        Order order = manager.createQuery(FIND_BY_ID, Order.class).setParameter("id", key).getSingleResult();
        if (order == null) {
            log.warn("Order with id {} not found", key);
            return false;
        }
        order.setStatus(CANCELED);
        manager.merge(order);
        log.info("Order is deleted");
        return true;
    }

    @Override
    public List<Order> findByUserId(User user) {
        log.info("Creating a list of orders matching the search criteria");
        TypedQuery<Order> query = manager.createQuery(FIND_BY_USER_ID, Order.class);
        query.setParameter("userId", user);
        return query.getResultList();
    }
}
