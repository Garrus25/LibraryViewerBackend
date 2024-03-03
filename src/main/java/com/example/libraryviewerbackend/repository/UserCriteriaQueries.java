package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.User;
import com.example.libraryviewerbackend.model.User_;
import com.example.libraryviewerbackend.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCriteriaQueries {

    public List<User> getAllUsers() {
        Session session = HibernateUtil.getHibernateSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        Query<User> query = session.createQuery(cr);
        return query.getResultList();
    }

    public User getUserById(Integer userId){
        Session session = HibernateUtil.getHibernateSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get(User_.ID), userId));
        Query<User> query = session.createQuery(cr);
        return query.getResultList().isEmpty() ? null : query.getSingleResult();
    }

    public Integer getMaxId() {
        Session session = HibernateUtil.getHibernateSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
        Root<User> root = cr.from(User.class);
        cr.select(cb.max(root.get(User_.ID)));
        Query<Integer> query = session.createQuery(cr);

        if (query.getResultList().isEmpty()){
            return 0;
        }
        return query.getSingleResult();
    }
}
