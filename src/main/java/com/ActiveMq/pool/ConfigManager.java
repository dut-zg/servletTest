package com.ActiveMq.pool;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private  static  HashMap<String,HashMap> map= new HashMap();
    static{
        HashMap<String, Integer> paraMap = new HashMap();
        paraMap.put("COREPOOL_SIZE",10);
        paraMap.put("MAXPOOL_SIZE",30);
        paraMap.put("KEEPALIVE_TIME",50);
        paraMap.put("WORK_QUEUE",10);
         map.put("JMS_BUSI_POOL",paraMap);
    }


    public static ConfigManager getSingleInstance(){


             return  new ConfigManager();
    }
    public int getParamInt(String poolName,String param){
        Map poolNameMap =(Map) map.get(poolName);
        return (Integer) poolNameMap.get(param);
    }
}
