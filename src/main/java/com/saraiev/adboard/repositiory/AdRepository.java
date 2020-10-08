package com.saraiev.adboard.repositiory;

import com.saraiev.adboard.domain.Ad;
import com.saraiev.adboard.payload.ad.AdFilterRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Transactional
@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ad> getAll() {
        return entityManager.createQuery("select a from Ad a order by a.id desc", Ad.class).getResultList();
    }

    public List<Ad> search(AdFilterRequest request) {
        StringBuilder querySb = new StringBuilder("select * from ad where title like :keyword\n");
        if (request.getUserId() != null) {
            querySb.append("and user_id = :user_id\n");
        }
        if (request.getDateAddedStart() != null) {
            querySb.append("and date_posted >= :date_posted_start\n");
        }
        if (request.getDateAddedEnd() != null) {
            querySb.append("and date_posted >= date_posted_end\n");
        }
        querySb.append(String.format("order by %s %s", request.getSortField(), request.getSortDirection()));

        Query query = entityManager.createNativeQuery(querySb.toString(), Ad.class)
                .setParameter("keyword", "%" + request.getKeyword() + "%");
        if (request.getUserId() != null) {
            query.setParameter("user_id", request.getUserId());
        }
        if (request.getDateAddedStart() != null) {
            query.setParameter("date_posted_start", request.getDateAddedStart());
        }
        if (request.getDateAddedEnd() != null) {
            query.setParameter("date_posted_end", request.getDateAddedEnd());
        }
        return query
                .setMaxResults(request.getSize())
                .setFirstResult((int) (request.getSize() * request.getPage()))
                .getResultList();
    }

    public Long searchTotalCount(AdFilterRequest request) {
        StringBuilder querySb = new StringBuilder("select count(*) from ad a where title like :keyword\n");
        if (request.getUserId() != null) {
            querySb.append("and user_id = :user_id\n");
        }
        if (request.getDateAddedStart() != null) {
            querySb.append("and date_posted >= :date_posted_start\n");
        }
        if (request.getDateAddedEnd() != null) {
            querySb.append("and date_posted >= date_posted_end\n");
        }
        Query query = entityManager.createNativeQuery(querySb.toString())
                .setParameter("keyword", "%" + request.getKeyword() + "%");

        if (request.getUserId() != null) {
            query.setParameter("user_id", request.getUserId());
        }
        if (request.getDateAddedStart() != null) {
            query.setParameter("date_posted_start", request.getDateAddedStart());
        }
        if (request.getDateAddedEnd() != null) {
            query.setParameter("date_posted_end", request.getDateAddedEnd());
        }
        BigInteger count = (BigInteger) query.getSingleResult();
        return count.longValue();
    }

    public Ad getById(Long id) {
        return entityManager.find(Ad.class, id);
    }

    public Ad save(Ad ad) {
        entityManager.persist(ad);
        return ad;
    }

    public void delete(Long id) {
        Ad ad = entityManager.find(Ad.class, id);
        if (ad != null) {
            entityManager.remove(ad);
        }
    }

}
