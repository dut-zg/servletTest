package com.ActiveMq.BusiProcessThread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.TextMessage;

public class BusiProcessThread implements Runnable{
    private TextMessage message;
    private static final Log log=  LogFactory.getLog(BusiProcessThread.class);
    @Override
    public void run() {
        try{
            long id = Thread.currentThread().getId();
            String messageTest = message.getText();
             String name = Thread.currentThread().getName();


//            if(id==15)
                Thread.currentThread().sleep(10000);
//            else
//                Thread.currentThread().sleep(50000);
            //  Thread.currentThread().sleep(10000);
            log.error("线程处理"+"线程号：<--"+id+"-->"+"线程名：<--"+name+"-->"+"输出信息：");

        }catch(Exception e){

        }
    }
    public BusiProcessThread(TextMessage message){
        this.message=message;

    }
}
