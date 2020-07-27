package com.ActiveMq.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolManger {
    protected ConfigManager configManager;//配置读取
    protected int maxPoolSize;//池中允许的最大现场数

    protected int corePoolSize;//池中保存的线程数
     protected int keepAliveTime;//当线程数大于核心时，终止多余的空闲线程等待新任务的最长时长。
    protected int workQueue;//阻塞队列的容器大小
    private static PoolManger singleManager;
    private PoolManger() {
    }

    public static PoolManger getInstance() {
        if (singleManager == null)
            singleManager = new PoolManger();
        return singleManager;
    }
    public PoolManger(int corePoolSize,int maximumPoolSize,int keepAliveTime,int workQueue){
        this.corePoolSize=corePoolSize;
        this.maxPoolSize=maximumPoolSize;
        this.keepAliveTime=keepAliveTime;
        this.workQueue=workQueue;

    }
    public ThreadPoolExecutor  getThreadPool(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(this.corePoolSize,
                this.maxPoolSize, this.keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue(workQueue),
                new ThreadPoolExecutor.CallerRunsPolicy());

        return threadPoolExecutor ;
    }
    public ThreadPoolExecutor getThreadPool(String poolName) {
        configManager = ConfigManager.getSingleInstance();
        corePoolSize = configManager.getParamInt(poolName, "COREPOOL_SIZE");
        maxPoolSize = configManager.getParamInt(poolName, "MAXPOOL_SIZE");
        keepAliveTime = configManager.getParamInt(poolName, "KEEPALIVE_TIME");
        workQueue = configManager.getParamInt(poolName, "WORK_QUEUE");
        return getThreadPool(corePoolSize, maxPoolSize, keepAliveTime,
                workQueue);
    }
    public ThreadPoolExecutor getThreadPool(int corePoolSize,
                                            int maximumPoolSize, int keepAliveTime, int workQueue) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue(workQueue),
                new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }
}
