package com.app.mvc.controller;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/1/24.
 */
public class ChatMessage {
    /**
     * 1.代表1对1聊天
     * 2.代表1对多聊天
     * 3.代表推送信息
     * */
    private Integer type;

    /**
     * 消息的来源
     * 用户的聊天id
     * */
    private String from;

    /**
     * 消息推送的地点
     * 1.推送用户的id
     * 2.聊天室的id
     */
    private String to;

    /**
     * 消息推送的内容
     * 1.推送的是字符串信息
     * 2.推送的是图片信息
     * 3.推送的是文件信息
     */
    private HashMap<Integer,String> context;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public HashMap<Integer, String> getContext() {
        return context;
    }

    public void setContext(HashMap<Integer, String> context) {
        this.context = context;
    }
}
