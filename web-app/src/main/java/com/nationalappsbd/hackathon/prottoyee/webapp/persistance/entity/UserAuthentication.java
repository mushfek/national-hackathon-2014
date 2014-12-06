package com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity;

import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.enums.UserAuthenticationStatus;

import javax.persistence.*;

/**
 * @author Abdullah Al Mamun Oronno
 */
@Entity
public class UserAuthentication extends Persistent{

    @Column(nullable = false, unique = true)
    private String authToken;

    private String verificationCode;

    @Enumerated
    private UserAuthenticationStatus status;

    private String deviceName;
    private String androidOS;
    private String imelNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public UserAuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(UserAuthenticationStatus status) {
        this.status = status;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAndroidOS() {
        return androidOS;
    }

    public void setAndroidOS(String androidOS) {
        this.androidOS = androidOS;
    }

    public String getImelNo() {
        return imelNo;
    }

    public void setImelNo(String imelNo) {
        this.imelNo = imelNo;
    }
}
