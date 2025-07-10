package br.com.projetoDP.utils;

import java.util.List;

public abstract class BaseService<T> {
    IBaseRepositoryBridge<T> repository;

    protected BaseService(IBaseRepositoryBridge<T> repository){
        this.repository  = repository;
    }

     public abstract List<T> findAll();

}
