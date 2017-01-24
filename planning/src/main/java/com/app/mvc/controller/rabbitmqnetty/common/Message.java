package com.app.mvc.controller.rabbitmqnetty.common;

/**
 * Created by Administrator on 2017/1/22.
 */
public class Message {

    private int type;

    private String context;

    //根据不同的类型，采用不同的规则
    public String getMessage(){
        return "";
    }


}
