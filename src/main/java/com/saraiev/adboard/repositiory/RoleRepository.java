package com.saraiev.adboard.repositiory;

import com.saraiev.adboard.domain.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getAll() {
        return entityManager.createQuery("from role r", Role.class).getResultList();
    }

    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }

    public Role create(Role role) {
        entityManager.persist(role);
        return role;
    }

}
