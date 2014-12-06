package com.nationalappsbd.hackathon.prottoyee.webapp.persistance.dao;

import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.User;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.UserAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Abdullah Al Mamun Oronno
 */
@Repository
@Transactional
public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext
    private EntityManager entityManager;


    public void save(User user) {
        entityManager.persist(user);
    }

    public void saveUserAuthentication(UserAuthentication userAuthentication) {
        entityManager.persist(userAuthentication);
    }

    public User findUserByMobile(String mobileNumber) {
        TypedQuery<User> query = entityManager.createQuery("SELECT o FROM User AS o WHERE o.mobileNumber=:mobileNumber", User.class);
        query.setParameter("mobileNumber", mobileNumber);

        return getSingleResultOrNull(query);
    }

    //reference: http://stackoverflow.com/questions/2002993/jpa-getsingleresult-or-null
    private User getSingleResultOrNull(TypedQuery<User> query) {
        query.setMaxResults(1);
        List<User> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public UserAuthentication findUserAuthentication(String authToken) {
        TypedQuery<UserAuthentication> query = entityManager.createQuery("SELECT o FROM UserAuthentication AS o WHERE o.authToken=:authToken", UserAuthentication.class);
        query.setParameter("authToken", authToken);
        return query.getSingleResult();
    }

    public List<UserAuthentication> findUserAuthentication(User user) {
        TypedQuery<UserAuthentication> query = entityManager.createQuery("SELECT o FROM UserAuthentication AS o WHERE o.user=:user", UserAuthentication.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
}
