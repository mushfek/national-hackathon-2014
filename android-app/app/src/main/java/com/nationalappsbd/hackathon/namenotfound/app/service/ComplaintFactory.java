package com.nationalappsbd.hackathon.namenotfound.app.service;

import com.nationalappsbd.hackathon.namenotfound.app.domain.Complaint;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bazlur on 12/6/14.
 */
public class ComplaintFactory {
    private static ComplaintFactory ourInstance = new ComplaintFactory();

    public static ComplaintFactory getInstance() {
        return ourInstance;
    }

    private static List<Complaint> complaintList = new LinkedList<Complaint>();

    private ComplaintFactory() {
    }

    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    public void addComplaint(Complaint complaint) {
        complaintList.add(complaint);
    }
}
