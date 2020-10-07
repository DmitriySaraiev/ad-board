package com.saraiev.adboard.repositiory;

import com.saraiev.adboard.domain.Ad;
import com.saraiev.adboard.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("from User u order by u.id desc", User.class).getResultList();
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

}
