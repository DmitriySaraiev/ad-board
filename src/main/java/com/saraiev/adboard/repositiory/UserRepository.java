package com.saraiev.adboard.repositiory;

import com.saraiev.adboard.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("from User u order by u.id desc", User.class).getResultList();
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getByLogin(String login) {
        return entityManager.createQuery("from User u where u.username = ?1", User.class)
                .setParameter(1, login)
                .getSingleResult();
    }

    public User getByLoginAndPassword(String login, String password) {
        return entityManager.createQuery("from User u where u.username = ?1 and u.password = ?2", User.class)
                .setParameter(1, login)
                .setParameter(2, password)
                .getSingleResult();
    }


    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

}
