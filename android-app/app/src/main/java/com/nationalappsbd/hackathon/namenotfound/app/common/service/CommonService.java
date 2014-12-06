package com.nationalappsbd.hackathon.namenotfound.app.common.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.loopj.android.http.AsyncHttpClient;
import com.nationalappsbd.hackathon.namenotfound.app.counseling.domain.Message;
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
    public List<Message> getMailList(int userId) {
        log.debug("userId={}", userId);

        return new ArrayList<Message>();
    }
}
