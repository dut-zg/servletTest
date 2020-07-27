package com.web.ActiveMq;

import com.ActiveMq.MqConsumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mqAction {

    public void mqStart(HttpServletRequest request, HttpServletResponse response) throws Exception {

        new MqConsumer().start();
    }
    public void mqStop(HttpServletRequest request, HttpServletResponse response) throws Exception {

        new MqConsumer().close();
    }
}
