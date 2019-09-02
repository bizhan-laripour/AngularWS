package com.dpco.business.dao;


import com.dpco.business.exception.CustomException;
import com.dpco.logger.Logger4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;

@Component
public class GenericDao<E, T> {

    @Autowired
    private Logger4j logger4j;

    public E save(E e) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(e);
            transaction.commit();
            session.close();
            sessionFactory.close();
            return e;
        }catch (Exception ex){
            throw new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(E e) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(e);
            transaction.commit();
            session.close();

            sessionFactory.close();
        }catch(Exception ex){
            throw new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public E update(E e) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(e);
            transaction.commit();
            session.close();
            sessionFactory.close();
            return e;
        }catch(Exception ex){
            throw new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public List<E> findAll(E e) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<E> list = session.createQuery("from " + e.getClass().getSimpleName()).list();
            transaction.commit();
            session.close();
            sessionFactory.close();
            return list;
        }catch (Exception ex){
            throw new CustomException(ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public E findById(E e, T t) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from " + e.getClass().getSimpleName() + " where id=:t");
            query.setParameter("t", t);
            List<E> list = query.list();
            transaction.commit();
            session.close();
            sessionFactory.close();
            return list.get(0);
        }catch (Exception ex){
            throw new CustomException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
