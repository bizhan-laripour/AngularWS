package com.dpco.dao;


import com.dpco.exception.CustomException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class GenericDao<E, T> {

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
        }catch (CustomException ex){
            throw new CustomException("some exception in saving the member", HttpStatus.INTERNAL_SERVER_ERROR);
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
        }catch(CustomException ex){
            throw new CustomException("some exception in deleting the member", HttpStatus.INTERNAL_SERVER_ERROR);
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
        }catch(CustomException ex){
            throw new CustomException("some exception in updating the member", HttpStatus.INTERNAL_SERVER_ERROR);

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
        }catch (CustomException ex){
            throw new CustomException("some exception in fetching all the member" , HttpStatus.INTERNAL_SERVER_ERROR);

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
        }catch (CustomException ex){
            throw new CustomException("some exception in finding the member", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
