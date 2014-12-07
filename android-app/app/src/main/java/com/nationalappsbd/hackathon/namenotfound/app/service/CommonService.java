package com.nationalappsbd.hackathon.namenotfound.app.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.loopj.android.http.AsyncHttpClient;
import com.nationalappsbd.hackathon.namenotfound.app.domain.AwarenessCategory;
import com.nationalappsbd.hackathon.namenotfound.app.domain.Message;
import com.nationalappsbd.hackathon.namenotfound.app.domain.OpenThread;
import com.oneous.log4android.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mushfekur Rahman
 */
@Singleton
public class CommonService {

    private static final Logger log = Logger.getLogger(CommonService.class);

    private AsyncHttpClient httpClient;

    @Inject
    public CommonService(AsyncHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    // TODO: implement it properly with async http client, currently it sends a dummy message list
    public List<Message> getMessageList(int userId) {
        log.debug("userId={}", userId);

        List<Message> messages = new ArrayList<Message>();

        messages.add(new Message(1, 12, "sfsdfksdlkfj dskfjlsdjflsdk dkfjsdlkfjsldk", "7th Dec, 2014",
                "10:42 AM", false));
        messages.add(new Message(1, 12, "sfsdfksdlkfj dskfjlsdjflsdk dkfjsdlkfjsldk", "7th Dec, 2014",
                "10:42 AM", false));
        messages.add(new Message(1, 12, "sfsdfksdlkfj dskfjlsdjflsdk dkfjsdlkfjsldk", "7th Dec, 2014",
                "10:42 AM", false));
        messages.add(new Message(1, 12, "sfsdfksdlkfj dskfjlsdjflsdk dkfjsdlkfjsldk", "7th Dec, 2014",
                "10:42 AM", false));

        return messages;
    }

    public List<AwarenessCategory> getCategories() {
        List<AwarenessCategory> awarenessCategories = new ArrayList<AwarenessCategory>();

        awarenessCategories.add(new AwarenessCategory(1, "General Experience Sharing"));
        awarenessCategories.add(new AwarenessCategory(2, "Laws and Regulations"));
        awarenessCategories.add(new AwarenessCategory(3, "Upcoming Campaigns"));
        awarenessCategories.add(new AwarenessCategory(4, "Others"));

        return awarenessCategories;
    }

    public List<OpenThread> getThreadList() {
        List<OpenThread> openThreads = new ArrayList<OpenThread>();

        openThreads.add(new OpenThread(1, "Thread title 1", "Blah blah blabity blah. Blah blah...", "Jane Doe", "Dec 6, 7:09 PM"));
        openThreads.add(new OpenThread(2, "Thread title 2", "Blah blah blabity blah. Blah blah...", "Jane Doe", "Dec 6, 7:09 PM"));
        openThreads.add(new OpenThread(3, "Thread title 3", "Blah blah blabity blah. Blah blah...", "Jane Doe", "Dec 6, 7:09 PM"));
        openThreads.add(new OpenThread(4, "Thread title 4", "Blah blah blabity blah. Blah blah...", "Jane Doe", "Dec 6, 7:09 PM"));
        openThreads.add(new OpenThread(5, "Thread title 5", "Blah blah blabity blah. Blah blah...", "Jane Doe", "Dec 6, 7:09 PM"));

        return openThreads;
    }
}
