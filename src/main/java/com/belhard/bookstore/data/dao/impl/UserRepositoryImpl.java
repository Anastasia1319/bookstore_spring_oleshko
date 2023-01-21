package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.UserRepository;
import com.belhard.bookstore.data.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@Transactional
public class UserRepositoryImpl implements UserRepository {
    private static final String FIND_ALL_ACTIVE = "from User where isActive = true";
    private static final String FIND_BY_EMAIL = "from User where email = :email AND isActive = true";
    private static final String FIND_BY_ID = "from User where id = :id and isActive = true";
    public static final String FIND_ALL = "from User";

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<User> findAll() {
        log.info("Created a list of users matching the search criteria");
        TypedQuery<User> query = manager.createQuery(FIND_ALL_ACTIVE, User.class);
        return query.getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("User with id {} found", id);
        TypedQuery<User> query = manager.createQuery(FIND_BY_ID, User.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public void save (User entity) {
        log.info("Trying to update a row with a user in the database");
        if (entity.getId() != null) {
            log.info("Updated the user");
            manager.merge(entity);
        } else {
            log.info("Creat the user");
            manager.persist(entity);
        }
    }

    @Override
    public boolean delete(Long id) {
        log.info("Trying to delete a row with a user in the database");
        User user = manager.createQuery(FIND_BY_ID, User.class).setParameter("id", id).getSingleResult();
        if (user == null) {
            log.warn("User with id {} not found amount active users", id);
            return false;
        }
        user.setActive(false);
        manager.merge(user);
        log.info("User is deleted");
        return true;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("User with email {} found", email);
        TypedQuery<User> query = manager.createQuery(FIND_BY_EMAIL, User.class);
        query.setParameter("email", email);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public int countAll() {
        log.info("Created a database call - counting the number of rows");
        return findAll().size();
    }

    @Override
    public List<User> findAllWithNotActive() {
        log.info("Created a list of all users (active and not-active) matching the search criteria");
        return manager.createQuery(FIND_ALL, User.class).getResultList();
    }
}