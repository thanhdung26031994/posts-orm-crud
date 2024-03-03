package org.example.postsormcrud.service;
import org.example.postsormcrud.model.Posts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PostsService implements IPostsService{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Posts> findAll() {
        String queryS = "SELECT p FROM Posts AS p";
        TypedQuery<Posts> query =entityManager.createQuery(queryS, Posts.class);
        return query.getResultList();
    }

    @Override
    public void save(Posts posts) {
        Transaction transaction = null;
        Posts origin;
        if (posts.getId() == 0) {
            origin = new Posts();
        } else {
            origin = findById(posts.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setCode(posts.getCode());
            origin.setTitle(posts.getTitle());
            origin.setContent(posts.getContent());
            origin.setDescription(posts.getDescription());
            origin.setImg(posts.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public Posts findById(int id) {
        String queryStr = "SELECT p FROM Posts AS p WHERE p.id = :id";
        TypedQuery<Posts> query = entityManager.createQuery(queryStr, Posts.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
