package com.saraiev.adboard.repositiory;

import com.saraiev.adboard.domain.Ad;
import com.saraiev.adboard.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class AdDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ad> getAll() {
        return entityManager.createQuery("from Ad a order by a.id desc", Ad.class).getResultList();
    }

    public Ad getById(int id) {
        return entityManager.find(Ad.class, id);
    }

    public Ad create(Ad ad) {
        entityManager.persist(ad);
        return ad;
    }

}
