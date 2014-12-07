package com.nationalappsbd.hackathon.namenotfound.app.domain;

import java.io.Serializable;

/**
 * @author Mushfekur Rahman
 */
public class Message implements Serializable {

    private int id;
    private int parentId;
    private int senderId;
    private String messageBody;
    private String sentDate;
    private String sentTime;
    private boolean sentByCounselor;

    public Message() {
    }

    public Message(String messageBody) {
        this.messageBody = messageBody;
    }

    public Message(int parentId, String messageBody) {
        this.parentId = parentId;
        this.messageBody = messageBody;
    }

    public Message(int id, int parentId, String messageBody, String sentDate) {
        this.id = id;
        this.parentId = parentId;
        this.messageBody = messageBody;
        this.sentDate = sentDate;
    }

    public Message(int id, int parentId, String messageBody, String sentDate,
                   String sentTime, boolean sentByCounselor) {
        this.id = id;
        this.parentId = parentId;
        this.messageBody = messageBody;
        this.sentDate = sentDate;
        this.sentTime = sentTime;
        this.sentByCounselor = sentByCounselor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public boolean isSentByCounselor() {
        return sentByCounselor;
    }

    public void setSentByCounselor(boolean sentByCounselor) {
        this.sentByCounselor = sentByCounselor;
    }
}
