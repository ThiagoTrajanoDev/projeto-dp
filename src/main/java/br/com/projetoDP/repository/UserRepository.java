package br.com.projetoDP.repository;

import br.com.projetoDP.domain.UserObserver;
import br.com.projetoDP.utils.IBaseRepositoryBridge;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements IBaseRepositoryBridge<UserObserver> {
}
