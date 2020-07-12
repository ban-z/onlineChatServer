package com.example.onlinechat.Data;

import java.io.Serializable;

/*
* 每个客户端对应一个User
* 用于服务端与数据库的交互，完成对用户账号，密码，状态的存储的载体
* */
public class User implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private String operation;
    private int acount;
    private String password;
    private int state;

    public User(String operation, int acount, String password) {
        this.operation = operation;
        this.acount = acount;
        this.password = password;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getAcount() {
        return acount;
    }

    public void setAcount(int acount) {
        this.acount = acount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
