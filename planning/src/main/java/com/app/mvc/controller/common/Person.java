package com.app.mvc.controller.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Administrator on 2017/1/15.
 */
@Component

@XmlRootElement(name = "Person")
public class Person {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
