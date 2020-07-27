package com.ActiveMq;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

public class senMq implements Runnable{
    private Log log = LogFactory.getLog(senMq.class);
    private String quen;
    private int id;
    public   senMq(String quen,int id){
        this.quen=quen;
        this.id=id;
    }
    @Override
    public void run() {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;
         connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:60001");
        // connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://10.4.63.229:61616");

        System.out.println("线程"+this.id+"开始处理！");

         try {
             log.error("111111111111111111111111");
              connection = connectionFactory.createConnection();
             connection.start();

             session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
             destination = session.createQueue( this.quen);
             producer = session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
           //  Mqbean bean = new Mqbean();
            // bean.setId(this.id);
             JSONObject message = new JSONObject();
             List messList= new ArrayList<String>();
           //  for (int k = 0; k<100;k++){
                 messList.add("這是线程号+++对应的消息！！"+this.id);
      //        }
              message.putIfAbsent("meeage",messList);

             for(int i=0;i<200;i++){
                 message.put("ID",i);
//                 bean.setMessage("记录，这是第"+i+"条消息");
//                 bean.setMessageinfo(message);
//                 producer.send(session.createObjectMessage(bean));
             producer.send(session.createTextMessage(message.toString()));
             }
             session.close();
             producer.close();
             connection.close();
             System.out.println("线程"+this.id+"处理完成！");
         }catch (JMSException e) {
             log.error("綫程號"+this.id+"错误了：",e);
         }
    }
}
