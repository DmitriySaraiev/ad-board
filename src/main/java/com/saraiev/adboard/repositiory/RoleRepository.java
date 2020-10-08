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

    public Role getByName(String name) {
        return entityManager.createQuery("select r from  Role r where r.name = ?1", Role.class)
                .setParameter(1, name)
                .getSingleResult();
    }

    public Role create(Role role) {
        entityManager.persist(role);
        return role;
    }

}
