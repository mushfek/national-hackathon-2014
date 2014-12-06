package com.nationalappsbd.hackathon.prottoyee.webapp.persistance.dao;

import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Repository
@Transactional
public class AdminUserDao {
    private static final Logger log = LoggerFactory.getLogger(AdminUserDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void save(AdminUser adminUser) {
        entityManager.persist(adminUser);
    }

    public void update(AdminUser adminUser) {
        entityManager.merge(adminUser);
    }

    public AdminUser findUserByUsername(String username) {

        TypedQuery<AdminUser> query = entityManager.createQuery("SELECT u FROM AdminUser AS u WHERE u.username=:username", AdminUser.class);
        query.setParameter("username", username);

        return getSingleResultOrNull(query);
    }

    public AdminUser findUserByEmail(String email) {

        TypedQuery<AdminUser> query = entityManager.createQuery("SELECT u FROM AdminUser AS u WHERE u.email=:email", AdminUser.class);
        query.setParameter("email", email);

        return getSingleResultOrNull(query);
    }

    //reference: http://stackoverflow.com/questions/2002993/jpa-getsingleresult-or-null
    private AdminUser getSingleResultOrNull(TypedQuery<AdminUser> query) {
        query.setMaxResults(1);
        List<AdminUser> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<AdminUser> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM AdminUser AS u", AdminUser.class).getResultList();
    }
}
