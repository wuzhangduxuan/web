package com.app.mvc.controller.rabbitmqnetty.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */
public class User implements Serializable {

    private int id;

    private String user_Name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }
}
