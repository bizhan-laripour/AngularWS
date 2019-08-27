package com.dpco.service;

import com.dpco.dao.GenericDao;
import com.dpco.exception.CustomException;

import java.util.List;


public class GenericService<E , T> {

    private GenericDao<E , T> genericDao;

    public GenericService(GenericDao<E,T> genericDao){
        this.genericDao = genericDao;
    }

    public E save(E e){
        try {
            return genericDao.save(e);
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

    public void delete(E e){
        try {
            genericDao.delete(e);
        }catch(CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

    public E update(E e){
        try {
            return genericDao.update(e);
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage() , ex.getStatus());
        }
    }

    public List<E> findAll(E e){
        try {
            return genericDao.findAll(e);
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage(),ex.getStatus());
        }
    }

    public E findById(E e , T t){
        try {
            return genericDao.findById(e, t);
        }catch (CustomException ex){
            throw new CustomException(ex.getMessage(),ex.getStatus());
        }
    }

}
