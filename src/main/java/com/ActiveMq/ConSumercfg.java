package com.ActiveMq;

import org.apache.activemq.ActiveMQConnection;

public class ConSumercfg {

    private String subject;//消息主题
    private boolean topic;//广播
    private String user= ActiveMQConnection.DEFAULT_USER;//用户名
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;//密码
    private String url;//端口地址
    private boolean transacted;//事务一致性
    private boolean durable;//是否持久订阅
    private String clientId;//消费者id
    private int ackMode;//确认模式
    private String consumerName;//消费者名称
    private long receiveTimeOut;//接收超时时间

    private int corePoolSize = 20;
    private int maximumPoolSize = 100;
    private int keepAliveTime = 30;
    private int workQueue = 6;

    public ConSumercfg(String url, String subject) {
        this.subject = "TOOL.DEFAULT";
        topic = false;
        user = ActiveMQConnection.DEFAULT_USER;
        password = ActiveMQConnection.DEFAULT_PASSWORD;
        this.url = "tcp://localhost:60001";
        transacted = false;
        durable = false;
        ackMode = 1;
        consumerName = "cbossprocess";
        receiveTimeOut = 0L;
        this.url = url;
        this.subject = subject;
    }
    /**
     * @return the corePoolSize
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }
    /**
     * @param corePoolSize the corePoolSize to set
     */
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }
    /**
     * @return the maximumPoolSize
     */
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }
    /**
     * @param maximumPoolSize the maximumPoolSize to set
     */
    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }
    /**
     * @return the keepAliveTime
     */
    public int getKeepAliveTime() {
        return keepAliveTime;
    }
    /**
     * @param keepAliveTime the keepAliveTime to set
     */
    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }
    /**
     * @return the workQueue
     */
    public int getWorkQueue() {
        return workQueue;
    }
    /**
     * @param workQueue the workQueue to set
     */
    public void setWorkQueue(int workQueue) {
        this.workQueue = workQueue;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * @return the topic
     */
    public boolean isTopic() {
        return topic;
    }
    /**
     * @param topic the topic to set
     */
    public void setTopic(boolean topic) {
        this.topic = topic;
    }
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the transacted
     */
    public boolean isTransacted() {
        return transacted;
    }
    /**
     * @param transacted the transacted to set
     */
    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }
    /**
     * @return the durable
     */
    public boolean isDurable() {
        return durable;
    }
    /**
     * @param durable the durable to set
     */
    public void setDurable(boolean durable) {
        this.durable = durable;
    }
    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }
    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    /**
     * @return the ackMode
     */
    public int getAckMode() {
        return ackMode;
    }
    /**
     * @param ackMode the ackMode to set
     */
    public void setAckMode(int ackMode) {
        this.ackMode = ackMode;
    }
    /**
     * @return the consumerName
     */
    public String getConsumerName() {
        return consumerName;
    }
    /**
     * @param consumerName the consumerName to set
     */
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }
    /**
     * @return the receiveTimeOut
     */
    public long getReceiveTimeOut() {
        return receiveTimeOut;
    }
    /**
     * @param receiveTimeOut the receiveTimeOut to set
     */
    public void setReceiveTimeOut(long receiveTimeOut) {
        this.receiveTimeOut = receiveTimeOut;
    }



}
