package com.example.onlinechat.Data;

import java.io.Serializable;
/*
* Message作为局域网通信中消息传递的载体，完成客户端与服务端之间的通信
*        具体消息的解析，传送功能由服务端实现
* */
public class Message implements Serializable {

    private String type;
    private int sender;
    private int receiver;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
