package br.com.projetoDP.repository;

import br.com.projetoDP.domain.UserObserver;
import br.com.projetoDP.utils.IBaseRepositoryBridge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UserRepository implements IBaseRepositoryBridge<UserObserver> {

    @PersistenceContext
    EntityManager em;

    public UserObserver findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM UserObserver u WHERE u.email = :email", UserObserver.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
