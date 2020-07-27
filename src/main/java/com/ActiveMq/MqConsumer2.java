package com.ActiveMq;

import com.ActiveMq.pool.PoolManger;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

public class MqConsumer2 {


    private Log log = LogFactory.getLog(MqConsumer.class);

    public static void main(String[] args) throws Exception {
        ////new MqConsumer().testQueueConsumer("11111111111111111");
        // new MqConsumer().testQueueConsumer("2222222222222222");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String format = dateFormat.format(date);

        String quen = "mystyle-quend";
//        for(int i=1;i<11;i++){
//            MqConsumerrun senMq=   new MqConsumerrun(quen,0);
//            new Thread(senMq).start();
//        }

        PoolManger poolManger = new PoolManger(5, 10, 50, 20);
        ThreadPoolExecutor threadPool = poolManger.getThreadPool();
        //  threadPool.execute(new MqConsumerrun(quen,0));
        System.out.println("11111");
        // threadPool.execute(new MqConsumerrun(quen,0));
        System.out.println("11111");
    }

    public void testQueueConsumer(String aa) throws Exception {
        System.out.println("这是接收到的消息：" + aa);
        // 创建一个ConnectionFactory对象连接MQ服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:60001");
        // 创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 使用Connection对象创建一个Session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建一个Destination对象。queue对象
        Queue queue = session.createQueue("mystyle-quend");
        // 使用Session对象创建一个消费者对象。
        MessageConsumer consumer = session.createConsumer(queue);
        // 接收消息
        consumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                // 打印结果
                TextMessage textMessage = (TextMessage) message;
                String text;
                try {
                    text = textMessage.getText();
                    System.out.println("这是接收到的消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
        // 等待接收消息
        System.in.read();
        // 关闭资源
        consumer.close();
        session.close();
        connection.close();


    }
}
