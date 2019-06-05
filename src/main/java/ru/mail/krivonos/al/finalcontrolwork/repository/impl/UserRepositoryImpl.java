package ru.mail.krivonos.al.finalcontrolwork.repository.impl;

import org.springframework.stereotype.Repository;
import ru.mail.krivonos.al.finalcontrolwork.repository.UserRepository;
import ru.mail.krivonos.al.finalcontrolwork.repository.model.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository("userRepository")
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User findUserByUsername(String username) {
        String queryString = String.format("from %s %s", entityClass.getName(),
                " where username = :username");
        Query query = entityManager.createQuery(queryString).setParameter("username", username);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
