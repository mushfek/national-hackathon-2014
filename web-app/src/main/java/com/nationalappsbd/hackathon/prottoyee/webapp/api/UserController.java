package com.nationalappsbd.hackathon.prottoyee.webapp.api;

import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.User;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.UserAuthentication;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.enums.UserAuthenticationStatus;
import com.nationalappsbd.hackathon.prottoyee.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdullah Al Mamun Oronno
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register-mobile", method = RequestMethod.POST)
    public Map registerMobileNumber(@RequestParam String mobileNumber) {
        log.info("registerMobileNumber, {}", mobileNumber);

        UserAuthentication userAuthentication = userService.registerNewUserAuthentication(mobileNumber);

        Map<String, String> response = new HashMap<>();
        response.put("authToken", userAuthentication.getAuthToken());
        return response;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public User verifyAccount(@RequestParam String mobileNumber,
                              @RequestParam String authToken,
                              @RequestParam String verificationCode) {
        log.info("verifyAccount, mobileNumber={}, authToken={}, verificationCode={}",
                mobileNumber, authToken, verificationCode);

        return userService.verifyUserAuthentication(mobileNumber, authToken, verificationCode);
    }

//    @RequestMapping(value = "/registration-complete", method = RequestMethod.POST)
//    public User completeRegistration(@RequestParam String mobileNumber,
//                                     @RequestParam String authToken,
//                                     @RequestParam String fullName,
//                                     @RequestParam String dateOfBirth,
//                                     @RequestParam Integer cityId) {
//        log.info("verifyAccount, mobileNumber={}, authToken={}", mobileNumber, authToken);
//
//
//
//    }


}
