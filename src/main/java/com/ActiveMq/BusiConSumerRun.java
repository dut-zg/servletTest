package com.ActiveMq;

import com.ActiveMq.BusiProcessThread.BusiProcessThread;
import com.ActiveMq.pool.PoolManger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.concurrent.ExecutorService;

public class BusiConSumerRun extends ConSumerRun {

    private static final Log log = LogFactory.getLog(BusiConSumerRun.class);
    private ExecutorService busiPool;

    public BusiConSumerRun(BusiConSumercfg cfg) throws JMSException {
        super(cfg);
        this.busiPool = PoolManger.getInstance().getThreadPool(
                cfg.getDisposePoolName());
        init(cfg);
    }


      public void run(TextMessage message) {
        try {
            long id = Thread.currentThread().getId();
            String name = Thread.currentThread().getName();
            String messageTest = message.getText();
           //  Thread.currentThread().sleep(10000);
            log.error("线程整体调度开始"+"线程号：<--"+id+"-->"+"线程名：<--"+name+"-->"+"输出信息：" );
            busiPool.execute(new BusiProcessThread(message));
            log.error("线程整体调度结束"+"线程号：<--"+id+"-->"+"线程名：<--"+name+"-->"  );

        } catch (Exception e) {
            //e.printStackTrace();
            log.error("error:",e);
        }
    }
    //重连
    public void renew() throws Exception {
        close();
        this.busiPool=null;
         this.busiPool = PoolManger.getInstance().getThreadPool("JMS_BUSI_POOL");
        init(cfg);
    }
    public void close() {
        super.close();
        busiPool.shutdown();
    }

}
