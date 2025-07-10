package br.com.projetoDP.utils;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface IBaseRepositoryBridge<T> extends PanacheRepository<T> {
}
