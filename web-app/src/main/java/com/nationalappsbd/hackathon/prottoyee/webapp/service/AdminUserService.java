package com.nationalappsbd.hackathon.prottoyee.webapp.service;

import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.dao.AdminUserDao;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Abdullah Al Mamun Oronno
 */
@Service
@Transactional
public class AdminUserService {
    private static final Logger log = LoggerFactory.getLogger(AdminUserService.class);

    @Autowired
    private AdminUserDao adminUserDao;

    public AdminUser findUserByUsername(String username) {
        return adminUserDao.findUserByUsername(username);
    }

    public AdminUser findUserByEmail(String email) {
        return adminUserDao.findUserByEmail(email);
    }

    public List<AdminUser> findAllUsers() {
        return adminUserDao.findAllUsers();
    }

    public void saveUser(AdminUser adminUser) {
        adminUserDao.save(adminUser);
    }
}
