package com.ActiveMq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;

public class MqConsumer {
    private String QUEUE="QUEUE_TEST_";
    private static final Log log= LogFactory.getLog(MqConsumer.class);
     private List<BusiConSumerRun> BusiConSumerRun=new ArrayList<BusiConSumerRun>();
    public static void main(String[] args)throws Exception{


    new MqConsumer().start();



     }

     public void start() throws JMSException {

         log.error("启动业务处理线程...");

         for (int i = 0; i <11; i++) {
             BusiConSumercfg busiConSumercfg = new BusiConSumercfg("tcp://localhost:60001", QUEUE+i, "JMS_BUSI_POOL");
             BusiConSumerRun busiConSumerRun = new BusiConSumerRun(busiConSumercfg);
             BusiConSumerRun.add(busiConSumerRun);

         }
         log.error("启动业务处理线程完成！");
     }
    public void close(){
        try {
//			log.error("关闭消息生产线程...");
//			if (busiReceiveThreads != null)
//				for (ProducerThread receiveThread : busiReceiveThreads) {
//					receiveThread.close();
//				}
//			log.error("关闭消息生产线程完成！");
            // 关闭业务处理线程
            log.error("关闭业务处理线程...");
            if (BusiConSumerRun != null)
                for (BusiConSumerRun busiConsumer : BusiConSumerRun) {
                    busiConsumer.close();
                }
            log.error("关闭业务处理线程完成！");
            Thread.sleep(30 * 1000);// 休眠<等待业务处理完成>
            log.error("关闭消息处理服务完成！");
            // 退出java虚拟机
            System.exit(0);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
