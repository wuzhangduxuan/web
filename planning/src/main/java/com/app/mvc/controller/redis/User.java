package com.app.mvc.controller.redis;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/25.
 */
public class User implements Serializable {

    private String id;

    private String name;

    private String pass;

    public User() {

    }

    public User(String id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
