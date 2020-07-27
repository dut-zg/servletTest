package com.ActiveMq;

public class BusiConSumercfg extends ConSumercfg{

    private String disposePoolName = "JMS_BUSI_POOL";

    public BusiConSumercfg(String url, String subject) {
        super(url, subject);
    }
    public BusiConSumercfg(String url, String subject, String disposePoolname) {
        super(url, subject);
        this.disposePoolName = disposePoolname;
    }

    public String getDisposePoolName() {
        return disposePoolName;
    }

    public void setDisposePoolName(String disposePoolName) {
        this.disposePoolName = disposePoolName;
    }


}
