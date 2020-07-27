package com.ActiveMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import java.util.function.Consumer;

public abstract class ConSumerRun implements MessageListener, ExceptionListener {

    private   Session session;
    private Connection connection;
    private String name;
    private Log log = LogFactory.getLog(MqConsumer.class);
    private long sum;
    private Boolean isConnect=Boolean.FALSE;
    protected  ConSumercfg cfg;
    private Destination destination;

    public ConSumerRun(ConSumercfg cfg){
        log = LogFactory.getLog(Consumer.class);
        connection = null;
        this.cfg = null;
        this.cfg = cfg;
        log.error((new StringBuilder()).append("客户端建立成功，队列SUBJECT ：")
                .append(cfg.getSubject()).toString());
    }

    public void init(ConSumercfg cfg) throws JMSException   {


            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    cfg.getUser(), cfg.getPassword(), cfg.getUrl());
            //  ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://10.4.63.229:61616");
            // 预取策略对象
            ActiveMQPrefetchPolicy prefetchPolicy = connectionFactory.getPrefetchPolicy();
            // 设置Queue的预取数量为50
            prefetchPolicy.setQueuePrefetch(50);
            connectionFactory.setPrefetchPolicy(prefetchPolicy);
            // 创建一个连接对象
            connection = connectionFactory.createConnection();
            connection.setExceptionListener(this);
            // 开启连接
            connection.start();
            // 使用Connection对象创建一个Session对象
            session = connection
                    .createSession(cfg.isTransacted(), cfg.getAckMode());
            // 创建一个Destination对象。queue对象
            if (cfg.isTopic()) {
                destination = session.createTopic(cfg.getSubject());
            }else {
                destination = session.createQueue(cfg.getSubject());
            }
            MessageConsumer consumer = null;
            if (cfg.isDurable() && cfg.isTopic()) {
                consumer = session.createDurableSubscriber((Topic) destination,
                        cfg.getConsumerName());
            }else {
                consumer = session.createConsumer(destination);
            }

            consumer.setMessageListener(this);
            isConnect =Boolean.TRUE;


    }



        public void onMessage(Message message) {
        try{
            if(message instanceof TextMessage){
                run((TextMessage)message);
            }else{

                log.error("接收到的message不是正确的MapMessage对象");
            }
            if (cfg.isTransacted())
                session.commit();
            else if (cfg.getAckMode() == 2)
                message.acknowledge();
            } catch (Exception e) {
        log.error(e.getMessage(),e);
             }


        }


     public abstract void run(TextMessage jmsinfo);

    public synchronized void onException(JMSException ex) {
        do {
            try {
                Thread.sleep(20000L);
                renew();
            } catch (Exception ex1) {
                log.error("", ex1);
                close();
                isConnect = Boolean.FALSE;
            }
        }while(!isConnect);
    }

    public void renew() throws Exception {
        close();
        init(this.cfg);
    }

    public void close() {
        try {
            if (session != null)
                session.close();
            if (connection != null)
                connection.close();
            log.debug("发送客户端已关闭完成");
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
