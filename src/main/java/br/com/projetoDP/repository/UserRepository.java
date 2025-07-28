package br.com.projetoDP.repository;

import br.com.projetoDP.entity.User;
import br.com.projetoDP.utils.IBaseRepositoryBridge;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UserRepository implements IBaseRepositoryBridge<User> {

    @PersistenceContext
    EntityManager em;

    public User findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
