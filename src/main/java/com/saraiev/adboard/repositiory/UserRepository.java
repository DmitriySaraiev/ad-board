package com.saraiev.adboard.repositiory;

import com.saraiev.adboard.domain.User;
import com.saraiev.adboard.error.CustomApiException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public User getById(Long id) {
        try {
            return entityManager.find(User.class, id);
        } catch (NoResultException e) {
            throw new CustomApiException("No such user");
        }
    }

    public User getByLogin(String username) {
        try {
            return entityManager.createQuery("from User u where u.username = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new CustomApiException("No such user");
        }
    }

    public User save(User user) {
        try {
            entityManager.persist(user);
            return user;
        } catch (Exception e) {
            throw new CustomApiException("User with such username already exists");
        }
    }

    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.getRoles().clear();
            entityManager.remove(user);
        }
    }

}
