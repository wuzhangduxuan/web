package com.app.mvc.controller.netty.Client;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/1/16.
 */
public class TestSpring {
    private static Logger log = Logger.getLogger(TestSpring.class);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //加载spirng配置文件
        ApplicationContext context= new
                ClassPathXmlApplicationContext("classpath:applicationContext.xml");


    }
}
