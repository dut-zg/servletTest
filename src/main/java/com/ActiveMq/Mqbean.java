package com.ActiveMq;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Mqbean  implements Serializable {
    private Integer id;
    private String message;
    private JSON info;

    public Integer getAge() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public JSON getMessageinfo() {
        return info;
    }
    public void setMessageinfo(JSON info) {
        this.info = info;
    }
}
