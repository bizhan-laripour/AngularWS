package com.dpco.business.service;

import com.dpco.business.dao.GenericRepository;

import java.io.Serializable;
import java.util.List;

public class GenericService<E , I extends Serializable> {

    protected GenericRepository<E , I> genericRepository;

    public GenericService(GenericRepository<E , I> genericRepository){
        this.genericRepository = genericRepository;
    }

    public E save(E e){
        return this.genericRepository.save(e);
    }

    public void delete(E e){
        this.genericRepository.delete(e);
    }

    public List<E> findAll(){
        return genericRepository.findAll();
    }


    public E findById(I id){
        return genericRepository.getOne(id);
    }
}
