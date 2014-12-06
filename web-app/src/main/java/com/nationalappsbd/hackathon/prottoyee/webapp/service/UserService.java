package com.nationalappsbd.hackathon.prottoyee.webapp.service;

import com.nationalappsbd.hackathon.prottoyee.webapp.exception.InvalidCredentialException;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.dao.UserDao;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.User;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.UserAuthentication;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.enums.UserAuthenticationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Abdullah Al Mamun Oronno
 */
@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;


    public void saveUser(User user) {
        userDao.save(user);
    }

    public void saveUserAuthentication(UserAuthentication userAuthentication) {
        userDao.saveUserAuthentication(userAuthentication);
    }

    public User findUserByMobile(String mobileNumber) {
        return userDao.findUserByMobile(mobileNumber);
    }

    public UserAuthentication findUserAuthentication(String authToken) {
        return userDao.findUserAuthentication(authToken);
    }

    public UserAuthentication registerNewUserAuthentication(String mobileNumber) {
        String authToken = UUID.randomUUID().toString();

        User user = findUserByMobile(mobileNumber);
        if (user == null) {
            user = new User();
            user.setMobileNumber(mobileNumber);
            saveUser(user);
            user.setPseudoName("user" + user.getId());
        }

        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setAuthToken(authToken);
//        Random rn = new Random();
//        rn.nextInt(900000) + 1
        userAuthentication.setVerificationCode("123456");
        userAuthentication.setStatus(UserAuthenticationStatus.PENDING);


        userAuthentication.setUser(user);
        saveUserAuthentication(userAuthentication);

        return userAuthentication;
    }

    public User verifyUserAuthentication(String mobileNumber, String authToken, String verificationCode) {
        UserAuthentication userAuthentication = findUserAuthentication(authToken);
        User user = userAuthentication.getUser();
        if (userAuthentication.getVerificationCode().equals(verificationCode)
                && user.getMobileNumber().equals(mobileNumber)
                && userAuthentication.getStatus() == UserAuthenticationStatus.PENDING) {

            List<UserAuthentication> existingUserAuthenticationList = userDao.findUserAuthentication(user);
            for (UserAuthentication existingUA : existingUserAuthenticationList) {
                if (existingUA.getStatus() == UserAuthenticationStatus.ACTIVE) {
                    existingUA.setStatus(UserAuthenticationStatus.DEACTIVATED);
                }
            }

            userAuthentication.setStatus(UserAuthenticationStatus.ACTIVE);

            return user;
        }

        throw new InvalidCredentialException("verifyUserAuthentication failed");
    }
}
